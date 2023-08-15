package br.com.douglasbello.restaurant.model.dtos;

import br.com.douglasbello.restaurant.model.dtos.DrinkItem.DrinkItemInputDTO;
import br.com.douglasbello.restaurant.model.dtos.FoodItem.FoodItemInputDTO;
import br.com.douglasbello.restaurant.model.entities.Drink;
import br.com.douglasbello.restaurant.model.entities.DrinkItem;
import br.com.douglasbello.restaurant.model.entities.Food;
import br.com.douglasbello.restaurant.model.entities.FoodItem;
import br.com.douglasbello.restaurant.services.impl.DrinkServiceImpl;
import br.com.douglasbello.restaurant.services.impl.FoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
