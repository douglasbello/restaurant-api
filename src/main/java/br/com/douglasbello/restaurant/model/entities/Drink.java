package br.com.douglasbello.restaurant.model.entities;

import java.math.BigDecimal;

import br.com.douglasbello.restaurant.model.enums.EnumDrinkSize;

public class Drink {
    private String name;
    private BigDecimal price;
    private EnumDrinkSize size;

    public Drink() {}

    public Drink(String name, BigDecimal price, EnumDrinkSize size) {
        this.name = name;
        this.price = price;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public EnumDrinkSize getSize() {
        return size;
    }

    public void setSize(EnumDrinkSize size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Drink [name=" + name + ", price=" + price + ", size=" + size + "]";
    }
}
