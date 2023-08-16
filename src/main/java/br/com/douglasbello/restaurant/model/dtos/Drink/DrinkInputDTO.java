package br.com.douglasbello.restaurant.model.dtos.Drink;

import br.com.douglasbello.restaurant.model.entities.Drink;
import br.com.douglasbello.restaurant.model.enums.EnumDrinkSize;
import br.com.douglasbello.restaurant.model.enums.EnumDrinkType;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class DrinkInputDTO {
    @NotBlank(message = "Drink name cannot be blank or null.")
    private String name;
    @NotBlank(message = "Drink size cannot be blank or null.")
    private EnumDrinkSize size;
    @NotBlank(message = "Drink soda brand cannot be blank or null.")
    private EnumDrinkType sodaBrand;
    @NotBlank(message = "Price cannot be blank or null.")
    private BigDecimal price;

    public DrinkInputDTO() {}

    public DrinkInputDTO(String name, EnumDrinkSize size, EnumDrinkType sodaBrand, BigDecimal price) {
        this.name = name;
        this.size = size;
        this.sodaBrand = sodaBrand;
        this.price = price;
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