package br.com.douglasbello.restaurant.controllers;

import br.com.douglasbello.restaurant.model.dtos.Drink.DrinkInputDTO;
import br.com.douglasbello.restaurant.model.dtos.Drink.DrinkResponseDTO;
import br.com.douglasbello.restaurant.model.dtos.Mapper;
import br.com.douglasbello.restaurant.model.entities.Drink;
import br.com.douglasbello.restaurant.model.enums.EnumDrinkSize;
import br.com.douglasbello.restaurant.model.enums.EnumDrinkType;
import br.com.douglasbello.restaurant.services.impl.DrinkServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/drink")
public class DrinkController {
    private final DrinkServiceImpl service;

    public DrinkController(DrinkServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DrinkResponseDTO>> findAll() {
        List<Drink> data = service.findAll();
        List<DrinkResponseDTO> response = data.stream().map(DrinkResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DrinkResponseDTO> findById(@PathVariable UUID id) {
        Drink drink = service.findById(id);
        DrinkResponseDTO response = new DrinkResponseDTO(drink);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<DrinkResponseDTO> save(@RequestBody DrinkInputDTO dto) {
        Drink data = Mapper.dtoToDrink(dto);
        DrinkResponseDTO response = new DrinkResponseDTO(service.save(data));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DrinkResponseDTO> update(@PathVariable UUID id, @RequestBody Drink newObj) {
        Drink drink = service.update(id, newObj);
        DrinkResponseDTO response = new DrinkResponseDTO(drink);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/price/asc")
    public ResponseEntity<List<DrinkResponseDTO>> findAllSortedByLowestPrice() {
        List<DrinkResponseDTO> data = service.findAllSortedByLowestPrice().stream().map(DrinkResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/price/desc")
    public ResponseEntity<List<DrinkResponseDTO>> findAllSortedByHighestPrice() {
        List<DrinkResponseDTO> data = service.findAllSortedByHighestPrice().stream().map(DrinkResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/size/{size}")
    public ResponseEntity<List<DrinkResponseDTO>> findAllBySize(@PathVariable String size) {
        List<DrinkResponseDTO> data = service.findAllBySize(EnumDrinkSize.valueOf(size)).stream().map(DrinkResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/sodaBrand/{sodaBrand}")
    public ResponseEntity<List<DrinkResponseDTO>> findAllBySodaBrand(@PathVariable String sodaBrand) {
        List<DrinkResponseDTO> data = service.findAllBySodaBrand(EnumDrinkType.valueOf(sodaBrand.toUpperCase())).stream().map(DrinkResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }
}