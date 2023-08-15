package br.com.douglasbello.restaurant.model.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.douglasbello.restaurant.model.entities.pk.DrinkItemPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DRINK_ITEMS")
public class DrinkItem {
    @EmbeddedId
    private DrinkItemPK id = new DrinkItemPK();
    private Integer quantity;
    private BigDecimal price;

    public DrinkItem() {}

    public DrinkItem(Integer quantity, BigDecimal price) {
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    @JsonIgnore
    public Drink getDrink() {
        return id.getDrink();
    }

    public void setDrink(Drink drink) {
        id.setDrink(drink);
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

    public void setPriceCalculatedByQuantity() {
        price = id.getDrink().getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}