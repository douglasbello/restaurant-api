package br.com.douglasbello.restaurant.model.dtos.Drink;

import br.com.douglasbello.restaurant.model.entities.Drink;
import br.com.douglasbello.restaurant.model.enums.EnumDrinkSize;
import br.com.douglasbello.restaurant.model.enums.EnumDrinkType;

import java.math.BigDecimal;
import java.util.UUID;

public class DrinkResponseDTO {
    private UUID id;
    private String name;
    private EnumDrinkSize size;
    private EnumDrinkType sodaBrand;
    private BigDecimal price;

    public DrinkResponseDTO() {}

    public DrinkResponseDTO(UUID id, String name, EnumDrinkSize size, EnumDrinkType sodaBrand, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.sodaBrand = sodaBrand;
        this.price = price;
    }

    public DrinkResponseDTO(Drink drink) {
        this.id = drink.getId();
        this.name = drink.getName();
        this.size = drink.getSize();
        this.sodaBrand = drink.getSodaBrand();
        this.price = drink.getPrice();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnumDrinkSize getSize() {
        return size;
    }

    public void setSize(EnumDrinkSize size) {
        this.size = size;
    }

    public EnumDrinkType getSodaBrand() {
        return sodaBrand;
    }

    public void setSodaBrand(EnumDrinkType sodaBrand) {
        this.sodaBrand = sodaBrand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}