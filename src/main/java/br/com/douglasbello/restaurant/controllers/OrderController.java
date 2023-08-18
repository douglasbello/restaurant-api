package br.com.douglasbello.restaurant.controllers;

import br.com.douglasbello.restaurant.model.dtos.Order.OrderInputDTO;
import br.com.douglasbello.restaurant.model.dtos.Order.OrderResponseDTO;
import br.com.douglasbello.restaurant.model.entities.Order;
import br.com.douglasbello.restaurant.services.impl.OrderServiceImpl;
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
@RequestMapping(value = "/order", produces = {"application/json"})
@Tag(name = "Order")
public class OrderController {
    private final OrderServiceImpl service;

    public OrderController(OrderServiceImpl service) {
        this.service = service;
    }

    @Operation(summary = "Returns all entities in the database.", method = "GET")
    @ApiResponse(responseCode = "200", description = "List of all entities registered in the database.")
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        List<Order> data = service.findAll();
        List<OrderResponseDTO> response = data.stream().map(OrderResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Finds entity by id and returns a DTO.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns DTO."),
            @ApiResponse(responseCode = "404", description = "Entity with provided id not found.")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable UUID id) {
        OrderResponseDTO dto = new OrderResponseDTO(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Receives a DTO, validates and converts it into an entity then inserts into the database.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Validated, converted and inserted successfully."),
            @ApiResponse(responseCode = "400", description = "The DTO was not validated successfully, error in one of the fields.")
    })
    @PostMapping
    public ResponseEntity<OrderResponseDTO> save(@Valid @RequestBody OrderInputDTO dto) {
        Order obj = service.createNewOrder(dto);
        obj.calculatePrice();
        OrderResponseDTO response = new OrderResponseDTO(service.save(obj));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Returns all Orders, sorted from lowest to highest price.", method = "GET")
    @ApiResponse(responseCode = "200", description = "Returns list of all Orders, sorted from lowest to highest price.")
    @GetMapping(value = "/price/asc")
    public ResponseEntity<List<OrderResponseDTO>> findAllSortedByLowestPrice() {
        List<OrderResponseDTO> response = service.findAllSortedByLowestPrice().stream().map(OrderResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Returns all Orders, sorted from highest to lowest price.", method = "GET")
    @ApiResponse(responseCode = "200", description = "Returns list of all Orders, sorted from highest to lowest price.")
    @GetMapping(value = "/price/desc")
    public ResponseEntity<List<OrderResponseDTO>> findAllSortedByHighestPrice() {
        List<OrderResponseDTO> response = service.findAllSortedByHighestPrice().stream().map(OrderResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}