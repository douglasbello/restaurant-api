package br.com.douglasbello.restaurant.model.entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

import br.com.douglasbello.restaurant.model.entities.interfaces.DrinkPriceCalculator;
import br.com.douglasbello.restaurant.model.entities.interfaces.impl.CocaColaPriceCalculator;
import br.com.douglasbello.restaurant.model.entities.interfaces.impl.GuaranaPriceCalculator;
import br.com.douglasbello.restaurant.model.entities.interfaces.impl.PepsiPriceCalculator;
import br.com.douglasbello.restaurant.model.enums.EnumDrinkSize;
import br.com.douglasbello.restaurant.model.enums.EnumDrinkType;
import jakarta.persistence.*;

@Entity
@Table(name = "DRINKS")
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private EnumDrinkSize size;
    @Enumerated(EnumType.STRING)
    private EnumDrinkType sodaBrand;
    private BigDecimal price = new BigDecimal(BigInteger.ZERO);

    @Transient
    private DrinkPriceCalculator priceCalculator;

    public Drink() {}

    public Drink(String name, BigDecimal price, EnumDrinkSize size, EnumDrinkType sodaBrand) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.sodaBrand = sodaBrand;

        setPriceCalculator();
        calculatePrice();
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
        setPriceCalculator();
    }

    public EnumDrinkType getSodaBrand() {
        return sodaBrand;
    }

    public void setSodaBrand(EnumDrinkType sodaBrand) {
        this.sodaBrand = sodaBrand;
        setPriceCalculator();
        calculatePrice();
    }

    private void setPriceCalculator() {
        if (sodaBrand == EnumDrinkType.COCA_COLA) {
            priceCalculator = new CocaColaPriceCalculator();
            return;
        }
        if (sodaBrand == EnumDrinkType.PEPSI) {
            priceCalculator = new PepsiPriceCalculator();
            return;
        }
        if (sodaBrand == EnumDrinkType.GUARANA) {
            priceCalculator = new GuaranaPriceCalculator();
            return;
        }
    }

    private void calculatePrice() {
        this.price = price.add(priceCalculator.calculatePrice(size));
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
        Drink other = (Drink) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Drink [id=" + id + ", name=" + name + ", price=" + price + ", size=" + size + ", sodaBrand=" + sodaBrand
                + "]";
    }
}
