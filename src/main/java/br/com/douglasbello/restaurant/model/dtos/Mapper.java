package br.com.douglasbello.restaurant.model.dtos;

import br.com.douglasbello.restaurant.model.dtos.Drink.DrinkInputDTO;
import br.com.douglasbello.restaurant.model.dtos.DrinkItem.DrinkItemDTO;
import br.com.douglasbello.restaurant.model.dtos.Food.FoodInputDTO;
import br.com.douglasbello.restaurant.model.dtos.FoodItem.FoodItemDTO;
import br.com.douglasbello.restaurant.model.dtos.Order.OrderInputDTO;
import br.com.douglasbello.restaurant.model.dtos.Order.OrderResponseDTO;
import br.com.douglasbello.restaurant.model.entities.*;
import br.com.douglasbello.restaurant.services.impl.DrinkItemServiceImpl;
import br.com.douglasbello.restaurant.services.impl.DrinkServiceImpl;
import br.com.douglasbello.restaurant.services.impl.FoodItemServiceImpl;
import br.com.douglasbello.restaurant.services.impl.FoodServiceImpl;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    private final DrinkServiceImpl drinkService;
    private final FoodServiceImpl foodService;


    public Mapper(DrinkServiceImpl drinkService, FoodServiceImpl foodService) {
        this.drinkService = drinkService;
        this.foodService = foodService;
    }

    public DrinkItem dtoToDrinkItem(DrinkItemDTO dto) {
        DrinkItem data = new DrinkItem();
        data.setQuantity(dto.getQuantity());
        Drink drink = drinkService.findById(dto.getDrinkId());
        data.setDrink(drink);
        data.setPriceCalculatedByQuantity();
        return data;
    }

    public FoodItem dtoToFoodItem(FoodItemDTO dto) {
        FoodItem data = new FoodItem();
        data.setQuantity(dto.getQuantity());
        Food food = foodService.findById(dto.getFoodId());
        data.setFood(food);
        data.setPriceCalculatedByQuantity();
        return data;
    }

    public Food dtoToFood(FoodInputDTO dto) {
        Food food = new Food();
        food.setName(dto.getName());
        food.setDescription(dto.getDescription());
        food.setPrice(dto.getPrice());
        food.setSize(dto.getSize());
        food.setType(dto.getType());
        return food;
    }

    public Drink dtoToDrink(DrinkInputDTO dto) {
        Drink drink = new Drink();
        drink.setName(dto.getName());
        drink.setSize(dto.getSize());
        drink.setSodaBrand(dto.getSodaBrand());
        return drink;
    }

    public Order dtoToOrder(OrderInputDTO dto) {
        Order order = new Order();
        order.setCep(dto.getCep());
        order.setHouseNumber(dto.getHouseNumber());
        order.setMoment(Instant.now());
        order.setPayment(dto.getPayment());

        List<FoodItem> foodItems = dto.getFoodItems().stream().map(this::dtoToFoodItem).collect(Collectors.toList());
        foodItems.forEach(item -> {
            item.setOrder(order);
            order.getFoodItems().add(item);
        });

        List<DrinkItem> drinkItems = dto.getDrinkItems().stream().map(this::dtoToDrinkItem).collect(Collectors.toList());
        drinkItems.forEach(item -> {
            item.setOrder(order);
            order.getDrinkItems().add(item);
        });

        return order;
    }
}