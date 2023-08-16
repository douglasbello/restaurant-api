package br.com.douglasbello.restaurant.model.entities;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.douglasbello.restaurant.model.entities.interfaces.PriceCalculator;
import br.com.douglasbello.restaurant.model.entities.interfaces.impl.BurguerPriceCalculator;
import br.com.douglasbello.restaurant.model.entities.interfaces.impl.DessertPriceCalculator;
import br.com.douglasbello.restaurant.model.entities.interfaces.impl.HotdogPriceCalculator;
import br.com.douglasbello.restaurant.model.entities.interfaces.impl.PizzaPriceCalculator;
import br.com.douglasbello.restaurant.model.enums.EnumFoodSize;
import br.com.douglasbello.restaurant.model.enums.EnumFoodType;
import jakarta.persistence.*;

@Entity
@Table(name = "FOODS")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private EnumFoodSize size;
    @Enumerated(EnumType.STRING)
    private EnumFoodType type;
    private BigDecimal price;

    @Transient
    private PriceCalculator priceCalculator;

    public Food() {}

    public Food(String name, String description, BigDecimal price, EnumFoodSize size, EnumFoodType type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.size = size;
        this.type = type;

//        setPriceCalculator();
//        calculatePrice();
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
        setPriceCalculator();
    }

    public EnumFoodType getType() {
        return type;
    }

    public void setType(EnumFoodType type) {
        this.type = type;
        setPriceCalculator();
        calculatePrice();
    }

    private void setPriceCalculator() {
        if (type == EnumFoodType.BURGUER) {
            this.priceCalculator = new BurguerPriceCalculator();
        } 
        if (type == EnumFoodType.PIZZA) {
            this.priceCalculator = new PizzaPriceCalculator();
        } 
        if (type == EnumFoodType.HOTDOG) {
            this.priceCalculator = new HotdogPriceCalculator();
        }
        if (type == EnumFoodType.DESSERT) {
            this.priceCalculator = new DessertPriceCalculator();
        }
    }

    private void calculatePrice() {
        this.price = price.add(priceCalculator.calculatePrice(size));
    }

    @Override
    public String toString() {
        return "Food [name=" + name + ", description=" + description + ", price=" + price + ", size=" + size + ", type="
                + type + "]";
    }
}
