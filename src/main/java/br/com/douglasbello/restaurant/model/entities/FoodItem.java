package br.com.douglasbello.restaurant.model.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.douglasbello.restaurant.model.entities.pk.FoodItemPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "FOOD_ITEMS")
public class FoodItem {
    @EmbeddedId
    private FoodItemPK id = new FoodItemPK();
    private Integer quantity;
    private BigDecimal price;

    public FoodItem() {}

    public FoodItem(Integer quantity, BigDecimal price) {
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);;
    }

    @JsonIgnore
    public Food getFood() {
        return id.getFood();
    }

    public void setFood(Food food) {
        id.setFood(food);
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
        price = id.getFood().getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FoodItem other = (FoodItem) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FoodItem [id=" + id + ", quantity=" + quantity + ", price=" + price + "]";
    }
}
