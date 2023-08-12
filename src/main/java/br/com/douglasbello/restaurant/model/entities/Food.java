package br.com.douglasbello.restaurant.model.entities;

import java.math.BigDecimal;
import java.util.UUID;

import br.com.douglasbello.restaurant.model.enums.EnumFoodSize;
import br.com.douglasbello.restaurant.model.enums.EnumFoodType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private EnumFoodSize size;
    private EnumFoodType type;

    public Food() {}

    public Food(String name, String description, BigDecimal price, EnumFoodSize size, EnumFoodType type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.size = size;
        this.type = type;
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

    public EnumFoodType getType() {
        return type;
    }

    public void setType(EnumFoodType type) {
        this.type = type;
    }

    private void calculatePrice() {
        if (this.type == EnumFoodType.BURGUER) {

            if (this.size == EnumFoodSize.MEDIUM) {
                this.price = price.add(new BigDecimal("4.99"));
            }

            if (this.size == EnumFoodSize.LARGE) {
                this.price = price.add(new BigDecimal("9.99"));
            }
        }

        else if (this.type == EnumFoodType.PIZZA) {
            if (this.size == EnumFoodSize.MEDIUM) {
                this.price = price.add(new BigDecimal("11.99"));
            }

            if (this.size == EnumFoodSize.LARGE) {
                this.price = price.add(new BigDecimal("20.99"));
            }
        }

        else if (this.type == EnumFoodType.HOTDOG) {
            if (this.size == EnumFoodSize.MEDIUM) {
                this.price = price.add(new BigDecimal("2.99"));
            }

            if (this.size == EnumFoodSize.LARGE) {
                this.price = price.add(new BigDecimal("6.99"));
            }
        }
    }

    @Override
    public String toString() {
        return "Food [name=" + name + ", description=" + description + ", price=" + price + ", size=" + size + ", type="
                + type + "]";
    }
}
