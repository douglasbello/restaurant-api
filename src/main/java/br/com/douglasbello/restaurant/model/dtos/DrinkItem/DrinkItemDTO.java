package br.com.douglasbello.restaurant.model.dtos.DrinkItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public class DrinkItemDTO {
    @NotNull(message = "The drinkId field cannot be null.")
    private UUID drinkId;
    @Positive(message = "Quantity field must be a positive integer.")
    private Integer quantity;

    public DrinkItemDTO() {}

    public DrinkItemDTO(UUID drinkId, Integer quantity) {
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