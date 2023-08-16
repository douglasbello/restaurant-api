package br.com.douglasbello.restaurant.model.dtos.Order;

import br.com.douglasbello.restaurant.model.entities.Order;
import br.com.douglasbello.restaurant.model.enums.EnumPayment;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderResponseDTO {
    private UUID id;
    private String cep;
    private String houseNumber;
    private String moment;
    private Set<OrderFoodItemResponseDTO> foodItems = new HashSet<>();
    private Set<OrderDrinkItemResponseDTO> drinkItems = new HashSet<>();
    private EnumPayment payment;
    private BigDecimal price;

    public OrderResponseDTO() {}

    public OrderResponseDTO(Order order) {
        this.id = order.getId();
        this.cep = order.getCep();
        this.houseNumber = order.getHouseNumber();
        this.moment = order.getFormattedMoment();
        this.payment = order.getPayment();
        this.price = order.getPrice();
        Set<OrderFoodItemResponseDTO> foodItemDTOS = order.getFoodItems().stream().map(OrderFoodItemResponseDTO::new).collect(Collectors.toSet());
        this.foodItems.addAll(foodItemDTOS);
        Set<OrderDrinkItemResponseDTO> drinkItemDTOS = order.getDrinkItems().stream().map(OrderDrinkItemResponseDTO::new).collect(Collectors.toSet());
        this.drinkItems.addAll(drinkItemDTOS);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getMoment() {
        return moment;
    }

    public void setMoment(String moment) {
        this.moment = moment;
    }

    public EnumPayment getPayment() {
        return payment;
    }

    public void setPayment(EnumPayment payment) {
        this.payment = payment;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<OrderFoodItemResponseDTO> getFoodItems() {
        return foodItems;
    }

    public Set<OrderDrinkItemResponseDTO> getDrinkItems() {
        return drinkItems;
    }
}