package br.com.douglasbello.restaurant.controllers;

import br.com.douglasbello.restaurant.model.entities.Drink;
import br.com.douglasbello.restaurant.services.impl.DrinkServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/drink")
public class DrinkController {
    private final DrinkServiceImpl service;

    public DrinkController(DrinkServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Drink>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Drink> findById(@PathVariable UUID id) {
        Drink drink = service.findById(id);
        return ResponseEntity.ok(drink);
    }

    @PostMapping
    public ResponseEntity<Drink> save(@RequestBody Drink drink) {
        BigDecimal price = new BigDecimal(String.valueOf(drink.getPrice()));
        drink.setPrice(price);
        drink = service.save(drink);
        return ResponseEntity.ok(drink);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Drink> update(@PathVariable UUID id, @RequestBody Drink newObj) {
        Drink drink = service.update(id, newObj);
        return ResponseEntity.ok(drink);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}