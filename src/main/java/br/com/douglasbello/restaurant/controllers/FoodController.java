package br.com.douglasbello.restaurant.controllers;

import br.com.douglasbello.restaurant.model.dtos.Food.FoodInputDTO;
import br.com.douglasbello.restaurant.model.dtos.Food.FoodResponseDTO;
import br.com.douglasbello.restaurant.model.dtos.Mapper;
import br.com.douglasbello.restaurant.model.entities.Food;
import br.com.douglasbello.restaurant.model.enums.EnumFoodSize;
import br.com.douglasbello.restaurant.model.enums.EnumFoodType;
import br.com.douglasbello.restaurant.services.impl.FoodServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/food")
public class FoodController {
    private final FoodServiceImpl service;

    public FoodController(FoodServiceImpl foodService) {
        this.service = foodService;
    }

    @GetMapping
    public ResponseEntity<List<FoodResponseDTO>> findAll() {
        List<Food> data = service.findAll();
        List<FoodResponseDTO> response = data.stream().map(FoodResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FoodResponseDTO> findById(@PathVariable UUID id) {
        FoodResponseDTO response = new FoodResponseDTO(service.findById(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<FoodResponseDTO> save(@RequestBody FoodInputDTO dto) {
        Food food = Mapper.dtoToFood(dto);
        FoodResponseDTO response = new FoodResponseDTO(service.save(food));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FoodResponseDTO> update(@PathVariable UUID id, @RequestBody FoodInputDTO dto) {
        FoodResponseDTO response = new FoodResponseDTO(service.updateUsingDTO(id, dto));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/price/asc")
    public ResponseEntity<List<FoodResponseDTO>> findAllSortedByLowestPrice() {
        List<FoodResponseDTO> data = service.findAllSortedByLowestPrice().stream().map(FoodResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/price/desc")
    public ResponseEntity<List<FoodResponseDTO>> findAllSortedByHighestPrice() {
        List<FoodResponseDTO> data = service.findAllSortedByHighestPrice().stream().map(FoodResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/type/{type}")
    public ResponseEntity<List<FoodResponseDTO>> findAllByType(@PathVariable String type) {
        List<FoodResponseDTO> data = service.findAllByFoodType(EnumFoodType.valueOf(type.toUpperCase())).stream().map(FoodResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/size/{size}")
    public ResponseEntity<List<FoodResponseDTO>> findAllBySize(@PathVariable String size) {
        List<FoodResponseDTO> data = service.findAllBySize(EnumFoodSize.valueOf(size.toUpperCase())).stream().map(FoodResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }
}