package br.com.douglasbello.restaurant.model.dtos.Order;

import br.com.douglasbello.restaurant.model.entities.Drink;
import br.com.douglasbello.restaurant.model.entities.DrinkItem;

import java.math.BigDecimal;

public class OrderDrinkItemResponseDTO {
    private Drink drink;
    private Integer quantity;
    private BigDecimal price;

    public OrderDrinkItemResponseDTO() {}

    public OrderDrinkItemResponseDTO(DrinkItem drinkItem) {
        this.drink = drinkItem.getDrink();
        this.quantity = drinkItem.getQuantity();
        setPriceCalculatedByQuantity();
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
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
        price = drink.getPrice().multiply(BigDecimal.valueOf(quantity));
    }
}