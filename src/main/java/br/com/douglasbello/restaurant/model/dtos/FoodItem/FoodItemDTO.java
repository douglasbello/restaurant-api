package br.com.douglasbello.restaurant.model.dtos.FoodItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public class FoodItemDTO {
    @NotBlank(message = "The drinkId field cannot be null or blank.")
    private UUID foodId;
    @Positive(message = "Quantity field must be a positive integer.")
    private Integer quantity;

    public FoodItemDTO() {}

    public FoodItemDTO(UUID foodId, Integer quantity) {
        this.foodId = foodId;
        this.quantity = quantity;
    }

    public UUID getFoodId() {
        return foodId;
    }

    public void setFoodId(UUID foodId) {
        this.foodId = foodId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}