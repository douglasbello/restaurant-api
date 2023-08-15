package br.com.douglasbello.restaurant.controllers;

import br.com.douglasbello.restaurant.model.entities.Food;
import br.com.douglasbello.restaurant.services.impl.FoodServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/food")
public class FoodController {
    private final FoodServiceImpl service;

    public FoodController(FoodServiceImpl foodService) {
        this.service = foodService;
    }

    @GetMapping
    public ResponseEntity<List<Food>> findAll() {
        List<Food> data = service.findAll();
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Food> findById(@PathVariable UUID id) {
        Food response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Food> save(@RequestBody Food food) {
        food = service.save(food);
        return ResponseEntity.ok(food);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Food> update(@PathVariable UUID id, @RequestBody Food food) {
        food = service.update(id, food);
        return ResponseEntity.ok(food);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}