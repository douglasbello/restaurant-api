package br.com.douglasbello.restaurant.model.dtos.Food;

import br.com.douglasbello.restaurant.model.entities.Food;
import br.com.douglasbello.restaurant.model.enums.EnumFoodSize;
import br.com.douglasbello.restaurant.model.enums.EnumFoodType;

import java.math.BigDecimal;
import java.util.UUID;

public class FoodResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private EnumFoodSize size;
    private EnumFoodType type;

    public FoodResponseDTO() {}

    public FoodResponseDTO(Food food) {
        id = food.getId();
        name = food.getName();
        description = food.getDescription();
        price = food.getPrice();
        size = food.getSize();
        type = food.getType();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public EnumFoodSize getSize() {
        return size;
    }

    public void setSize(EnumFoodSize size) {
        this.size = size;
    }

    public EnumFoodType getType() {
        return type;
    }

    public void setType(EnumFoodType type) {
        this.type = type;
    }
}