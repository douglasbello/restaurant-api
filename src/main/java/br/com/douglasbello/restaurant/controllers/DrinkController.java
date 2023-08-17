package br.com.douglasbello.restaurant.controllers;

import br.com.douglasbello.restaurant.model.dtos.Drink.DrinkInputDTO;
import br.com.douglasbello.restaurant.model.dtos.Drink.DrinkResponseDTO;
import br.com.douglasbello.restaurant.model.dtos.Mapper;
import br.com.douglasbello.restaurant.model.entities.Drink;
import br.com.douglasbello.restaurant.model.enums.EnumDrinkSize;
import br.com.douglasbello.restaurant.model.enums.EnumDrinkType;
import br.com.douglasbello.restaurant.services.impl.DrinkServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/drink", produces = {"application/json"})
@Tag(name = "Drink")
public class DrinkController {
    private final DrinkServiceImpl service;
    private final Mapper mapper;

    public DrinkController(DrinkServiceImpl service, Mapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Returns all Drinks registered in the database.", method = "GET")
    @ApiResponse(responseCode = "200", description = "List of all Drinks in the database, it can be an empty list if no drinks are registered.")
    @GetMapping
    public ResponseEntity<List<DrinkResponseDTO>> findAll() {
        List<Drink> data = service.findAll();
        List<DrinkResponseDTO> response = data.stream().map(DrinkResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Finds a Drink in the database by it's id.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the entity."),
            @ApiResponse(responseCode = "404", description = "Entity with the provided id not found.")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<DrinkResponseDTO> findById(@PathVariable UUID id) {
        Drink drink = service.findById(id);
        DrinkResponseDTO response = new DrinkResponseDTO(drink);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Receive a DrinkInputDTO, converts it into a Drink and inserts into the database.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inserted the entity."),
            @ApiResponse(responseCode = "400", description = "The DTO provided was not valid.")})
    @PostMapping
    public ResponseEntity<DrinkResponseDTO> save(@Valid @RequestBody DrinkInputDTO dto) {
        Drink data = mapper.dtoToDrink(dto);
        DrinkResponseDTO response = new DrinkResponseDTO(service.save(data));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Finds a entity by the id, updates it with the DTO and insert the Drink in the database again.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity found and updated."),
            @ApiResponse(responseCode = "400", description = "The DTO provided was not valid."),
            @ApiResponse(responseCode = "404", description = "Entity with the provided id not found.")})
    @PutMapping(value = "/{id}")
    public ResponseEntity<DrinkResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody DrinkInputDTO newObj) {
        Drink drink = service.updateUsingDTO(id, newObj);
        DrinkResponseDTO response = new DrinkResponseDTO(drink);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Finds a entity by the id and deletes it from the database.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Entity found and deleted."),
            @ApiResponse(responseCode = "404", description = "Entity with the provided id not found.")})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Returns all drinks sorted from lowest price to highest.", method = "GET")
    @ApiResponse(responseCode = "200", description = "List of all the drinks sorted from lowest price to highest.")
    @GetMapping(value = "/price/asc")
    public ResponseEntity<List<DrinkResponseDTO>> findAllSortedByLowestPrice() {
        List<DrinkResponseDTO> data = service.findAllSortedByLowestPrice().stream().map(DrinkResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Returns all drinks sorted from highest price to lowest.", method = "GET")
    @ApiResponse(responseCode = "200", description = "List of all the drinks sorted from highest price to lowest.")
    @GetMapping(value = "/price/desc")
    public ResponseEntity<List<DrinkResponseDTO>> findAllSortedByHighestPrice() {
        List<DrinkResponseDTO> data = service.findAllSortedByHighestPrice().stream().map(DrinkResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Returns all drinks with the provided size.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns list of all the drinks with the provided size."),
            @ApiResponse(responseCode = "400", description = "The size provided isn't valid.")
    })
    @GetMapping(value = "/size/{size}")
    public ResponseEntity<List<DrinkResponseDTO>> findAllBySize(@PathVariable String size) {
        List<DrinkResponseDTO> data = service.findAllBySize(EnumDrinkSize.valueOf(size)).stream().map(DrinkResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }

    @Operation(summary = "Returns all drinks with the provided soda brand.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns list of all the drinks with the provided soda brand."),
            @ApiResponse(responseCode = "400", description = "The soda brand provided isn't valid.")
    })
    @GetMapping(value = "/sodaBrand/{sodaBrand}")
    public ResponseEntity<List<DrinkResponseDTO>> findAllBySodaBrand(@PathVariable String sodaBrand) {
        List<DrinkResponseDTO> data = service.findAllBySodaBrand(EnumDrinkType.valueOf(sodaBrand.toUpperCase())).stream().map(DrinkResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }
}