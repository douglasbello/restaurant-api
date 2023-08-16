package br.com.douglasbello.restaurant.model.dtos.Order;

import br.com.douglasbello.restaurant.model.dtos.DrinkItem.DrinkItemDTO;
import br.com.douglasbello.restaurant.model.dtos.FoodItem.FoodItemDTO;
import br.com.douglasbello.restaurant.model.enums.EnumPayment;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class OrderInputDTO {
    @Size(min = 8, max = 8, message = "The cep field size length must be 8 characters.")
    private String cep;
    private String houseNumber;
    private Set<FoodItemDTO> foodItems = new HashSet<>();
    private Set<DrinkItemDTO> drinkItems = new HashSet<>();
    private EnumPayment payment;

    public OrderInputDTO() {}

    public OrderInputDTO(String cep, String houseNumber, EnumPayment payment) {
        this.cep = cep;
        this.houseNumber = houseNumber;
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

    public EnumPayment getPayment() {
        return payment;
    }

    public void setPayment(EnumPayment payment) {
        this.payment = payment;
    }

    public Set<FoodItemDTO> getFoodItems() {
        return foodItems;
    }

    public Set<DrinkItemDTO> getDrinkItems() {
        return drinkItems;
    }
}