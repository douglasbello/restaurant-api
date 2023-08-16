package br.com.douglasbello.restaurant.model.dtos.Order;

import br.com.douglasbello.restaurant.model.entities.Food;
import br.com.douglasbello.restaurant.model.entities.FoodItem;

import java.math.BigDecimal;

public class OrderFoodItemResponseDTO {
    private Food food;
    private Integer quantity;
    private BigDecimal price;

    public OrderFoodItemResponseDTO() {}

    public OrderFoodItemResponseDTO(FoodItem foodItem) {
        this.food = foodItem.getFood();
        this.quantity = foodItem.getQuantity();
        setPriceCalculatedByQuantity();
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPriceCalculatedByQuantity() {
        price = food.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}