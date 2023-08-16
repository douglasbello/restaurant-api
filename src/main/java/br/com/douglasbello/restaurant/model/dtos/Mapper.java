package br.com.douglasbello.restaurant.model.dtos;

import br.com.douglasbello.restaurant.model.dtos.DrinkItem.DrinkItemInputDTO;
import br.com.douglasbello.restaurant.model.dtos.Food.FoodInputDTO;
import br.com.douglasbello.restaurant.model.dtos.FoodItem.FoodItemInputDTO;
import br.com.douglasbello.restaurant.model.dtos.Order.OrderInputDTO;
import br.com.douglasbello.restaurant.model.entities.*;
import br.com.douglasbello.restaurant.services.impl.DrinkServiceImpl;
import br.com.douglasbello.restaurant.services.impl.FoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {

    @Autowired
    private static DrinkServiceImpl drinkService;

    @Autowired
    private static FoodServiceImpl foodService;

    public static DrinkItem dtoToDrinkItem(DrinkItemInputDTO dto) {
        DrinkItem data = new DrinkItem();
        data.setQuantity(dto.getQuantity());
        Drink drink = drinkService.findById(dto.getDrinkId());
        data.setDrink(drink);
        data.setPriceCalculatedByQuantity();
        return data;
    }

    public static FoodItem dtoToFoodItem(FoodItemInputDTO dto) {
        FoodItem data = new FoodItem();
        data.setQuantity(dto.getQuantity());
        Food food = foodService.findById(dto.getFoodId());
        data.setFood(food);
        data.setPriceCalculatedByQuantity();
        return data;
    }

    public static Food dtoToFood(FoodInputDTO dto) {
        Food food = new Food();
        food.setName(dto.getName());
        food.setDescription(dto.getDescription());
        food.setPrice(dto.getPrice());
        food.setSize(dto.getSize());
        food.setType(dto.getType());
        return food;
    }

    public static Order dtoToOrder(OrderInputDTO dto) {
        Order order = new Order();
        order.setCep(dto.getCep());
        order.setHouseNumber(dto.getHouseNumber());

        List<FoodItem> foodItems = dto.getFoodItemInputDTOS().stream().map(Mapper::dtoToFoodItem).collect(Collectors.toList());
        foodItems.forEach(item -> {
            item.setOrder(order);
            order.getFoodItems().add(item);
        });

        List<DrinkItem> drinkItems = dto.getDrinkItemInputDTOS().stream().map(Mapper::dtoToDrinkItem).collect(Collectors.toList());
        drinkItems.forEach(item -> {
            item.setOrder(order);
            order.getDrinkItems().add(item);
        });

        return order;
    }
}