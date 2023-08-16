package br.com.douglasbello.restaurant.model.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.douglasbello.restaurant.model.enums.EnumPayment;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String cep;
    private String houseNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant moment;
    @OneToMany(mappedBy = "id.order")
    private Set<FoodItem> foodItems = new HashSet<>();
    @OneToMany(mappedBy = "id.order")
    private Set<DrinkItem> drinkItems = new HashSet<>();
    private EnumPayment payment;
    private BigDecimal price = BigDecimal.ZERO;

    public Order() {}

    public Order(Instant moment, EnumPayment payment) {
        this.moment = moment;
        this.payment = payment;
    }

    public UUID getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public String getFormattedMoment() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
                .withZone(ZoneId.of("America/Sao_Paulo"));
        return formatter.format(moment);
    }

    public Set<FoodItem> getFoodItems() {
        return foodItems;
    }

    public Set<DrinkItem> getDrinkItems() {
        return drinkItems;
    }

    public EnumPayment getPayment() {
        return payment;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public void setPayment(EnumPayment payment) {
        this.payment = payment;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setFoodItems(Set<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public void setDrinkItems(Set<DrinkItem> drinkItems) {
        this.drinkItems = drinkItems;
    }

    public void calculatePrice() {

        foodItems.forEach(foodItem -> {
            BigDecimal priceTimesQuantity = foodItem.getFood().getPrice().multiply(BigDecimal.valueOf(foodItem.getQuantity()));
            this.price = this.price.add(priceTimesQuantity);
        });

        drinkItems.forEach(drinkItem -> {
            BigDecimal priceTimesQuantity = drinkItem.getDrink().getPrice().multiply(BigDecimal.valueOf(drinkItem.getQuantity()));
            this.price = this.price.add(priceTimesQuantity);
        });
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", moment=" + moment + ", foodItems=" + foodItems + ", drinkItems=" + drinkItems
                + ", payment=" + payment + "]";
    }
}
