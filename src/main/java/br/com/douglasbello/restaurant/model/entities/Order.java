package br.com.douglasbello.restaurant.model.entities;

import java.time.Instant;
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
@Table(name = "tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String cep;
    private String houseNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;
    @OneToMany(mappedBy = "id.order")
    private Set<FoodItem> foodItems = new HashSet<>();
    @OneToMany(mappedBy = "id.order")
    private Set<DrinkItem> drinkItems = new HashSet<>();
    private EnumPayment payment;

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
