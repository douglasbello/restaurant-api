package br.com.douglasbello.restaurant.model.entities;

import java.math.BigDecimal;

import br.com.douglasbello.restaurant.model.enums.EnumFoodSize;

public abstract class Food {
    protected String name;
    protected String description;
    protected BigDecimal price;
    protected EnumFoodSize size;

    public Food() {}

    public Food(String name, String description, BigDecimal price, EnumFoodSize size) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.size = size;
        calculatePrice();
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
        calculatePrice();
    }

    protected void calculatePrice() {
        if (this.size == EnumFoodSize.MEDIUM) {
            this.price = price.add(new BigDecimal("4.99"));
        }

        if (this.size == EnumFoodSize.LARGE) {
            this.price = price.add(new BigDecimal("9.99"));
        }
    }
}
