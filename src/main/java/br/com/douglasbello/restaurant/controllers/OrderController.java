package br.com.douglasbello.restaurant.controllers;

import br.com.douglasbello.restaurant.model.dtos.Mapper;
import br.com.douglasbello.restaurant.model.dtos.Order.OrderInputDTO;
import br.com.douglasbello.restaurant.model.dtos.Order.OrderResponseDTO;
import br.com.douglasbello.restaurant.model.entities.Order;
import br.com.douglasbello.restaurant.services.impl.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    private final OrderServiceImpl service;

    public OrderController(OrderServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        List<Order> data = service.findAll();
        List<OrderResponseDTO> response = data.stream().map(OrderResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable UUID id) {
        OrderResponseDTO dto = new OrderResponseDTO(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> save(@RequestBody OrderInputDTO dto) {
        Order obj = service.createNewOrder(dto);
        obj.calculatePrice();
        OrderResponseDTO response = new OrderResponseDTO(service.save(obj));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/price/asc")
    public ResponseEntity<List<OrderResponseDTO>> findAllSortedByLowestPrice() {
        List<OrderResponseDTO> response = service.findAllSortedByLowestPrice().stream().map(OrderResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/price/desc")
    public ResponseEntity<List<OrderResponseDTO>> findAllSortedByHighestPrice() {
        List<OrderResponseDTO> response = service.findAllSortedByHighestPrice().stream().map(OrderResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}