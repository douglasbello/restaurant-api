package br.com.douglasbello.restaurant.model.dtos.DrinkItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public class DrinkItemInputDTO {
    @NotBlank(message = "The drinkId field cannot be null or blank.")
    private UUID drinkId;
    @Positive(message = "Quantity field must be a positive integer.")
    private Integer quantity;

    public DrinkItemInputDTO() {}

    public DrinkItemInputDTO(UUID drinkId, Integer quantity) {
        this.drinkId = drinkId;
        this.quantity = quantity;
    }

    public UUID getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(UUID drinkId) {
        this.drinkId = drinkId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}