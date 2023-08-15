package br.com.douglasbello.restaurant.model.dtos.Food;

import br.com.douglasbello.restaurant.model.enums.EnumFoodSize;
import br.com.douglasbello.restaurant.model.enums.EnumFoodType;
import jakarta.validation.constraints.NotBlank;

public class FoodInputDTO {
    @NotBlank(message = "Name cannot be blank or null.")
    private String name;
    @NotBlank(message = "Description cannot be blank or null.")
    private String description;
    private EnumFoodSize size;
    private EnumFoodType type;

    public FoodInputDTO () {}

    public FoodInputDTO(String name, String description, EnumFoodSize size, EnumFoodType type) {
        this.name = name;
        this.description = description;
        this.size = size;
        this.type = type;
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