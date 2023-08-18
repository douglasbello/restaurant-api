package br.com.douglasbello.restaurant.controllers;

import br.com.douglasbello.restaurant.model.dtos.Food.FoodInputDTO;
import br.com.douglasbello.restaurant.model.dtos.Food.FoodResponseDTO;
import br.com.douglasbello.restaurant.model.dtos.Mapper;
import br.com.douglasbello.restaurant.model.entities.Food;
import br.com.douglasbello.restaurant.model.enums.EnumFoodSize;
import br.com.douglasbello.restaurant.model.enums.EnumFoodType;
import br.com.douglasbello.restaurant.services.impl.FoodServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/food", produces = {"application/json"})
@Tag(name = "Food")
public class FoodController {
    private final FoodServiceImpl service;
    private final Mapper mapper;

    public FoodController(FoodServiceImpl foodService, Mapper mapper) {
        this.service = foodService;
        this.mapper = mapper;
    }

    @Operation(summary = "Returns all Food entities in the database.", method = "GET")
    @ApiResponse(responseCode = "200", description = "List of all the entities registered.")
    @GetMapping
    public ResponseEntity<List<FoodResponseDTO>> findAll() {
        List<Food> data = service.findAll();
        List<FoodResponseDTO> response = data.stream().map(FoodResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Finds an entity by its id.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity found successfully, returns the entity."),
            @ApiResponse(responseCode = "403", description = "The token was not valid. Unauthorized."),
            @ApiResponse(responseCode = "404", description = "Entity with the provided not found.")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<FoodResponseDTO> findById(@PathVariable UUID id) {
        FoodResponseDTO response = new FoodResponseDTO(service.findById(id));
        return ResponseEntity.ok(response);
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Receives an dto, validates it, converts it into entity and inserts in the database.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Entity validated and inserted successfully."),
            @ApiResponse(responseCode = "403", description = "The token was not valid. Unauthorized."),
            @ApiResponse(responseCode = "400", description = "Error validating the DTO.")})
    @PostMapping
    public ResponseEntity<FoodResponseDTO> save(@Valid @RequestBody FoodInputDTO dto) {
        Food food = mapper.dtoToFood(dto);
        FoodResponseDTO response = new FoodResponseDTO(service.save(food));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Finds entity by the id provided, validates the DTO, update the entity using the dto, and insert the entity again.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity found, DTO validated, entity updated and inserted successfully."),
            @ApiResponse(responseCode = "400", description = "Error validating the DTO."),
            @ApiResponse(responseCode = "403", description = "The token was not valid. Unauthorized."),
            @ApiResponse(responseCode = "404", description = "Entity with the provided it not found.")})
    @PutMapping(value = "/{id}")
    public ResponseEntity<FoodResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody FoodInputDTO dto) {
        FoodResponseDTO response = new FoodResponseDTO(service.updateUsingDTO(id, dto));
        return ResponseEntity.ok(response);
    }

    @SecurityRequirement(name = "bearerAuth")
    @Operation(summary = "Finds entity by the id provided and deletes it from the database.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Entity found and deleted successfully."),
            @ApiResponse(responseCode = "403", description = "The token was not valid. Unauthorized."),
            @ApiResponse(responseCode = "404", description = "Entity with the provided id not found.")})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Returns all Food entities sorted from lowest price to highest.", method = "GET")
    @ApiResponse(responseCode = "200", description = "List of all entities sorted from lowest price to highest.")
    @GetMapping(value = "/price/asc")
    public ResponseEntity<List<FoodResponseDTO>> findAllSortedByLowestPrice() {
        List<FoodResponseDTO> data = service.findAllSortedByLowestPrice().stream().map(FoodResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Returns all Food entities sorted from highest price to lowest.", method = "GET")
    @ApiResponse(responseCode = "200", description = "List of all entities sorted from highest price to lowest.")
    @GetMapping(value = "/price/desc")
    public ResponseEntity<List<FoodResponseDTO>> findAllSortedByHighestPrice() {
        List<FoodResponseDTO> data = service.findAllSortedByHighestPrice().stream().map(FoodResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Finds all entities by the type provided.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of entities with the provided type."),
            @ApiResponse(responseCode = "400", description = "The type provided isn't valid.")})
    @GetMapping(value = "/type/{type}")
    public ResponseEntity<List<FoodResponseDTO>> findAllByType(@PathVariable String type) {
        List<FoodResponseDTO> data = service.findAllByFoodType(EnumFoodType.valueOf(type.toUpperCase())).stream().map(FoodResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Finds all entities by the size provided.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of entities with the provided size."),
            @ApiResponse(responseCode = "400", description = "The size provided isn't valid.")})
    @GetMapping(value = "/size/{size}")
    public ResponseEntity<List<FoodResponseDTO>> findAllBySize(@PathVariable String size) {
        List<FoodResponseDTO> data = service.findAllBySize(EnumFoodSize.valueOf(size.toUpperCase())).stream().map(FoodResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }
}