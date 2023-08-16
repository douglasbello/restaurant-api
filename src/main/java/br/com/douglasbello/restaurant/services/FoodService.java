package br.com.douglasbello.restaurant.services;

import br.com.douglasbello.restaurant.model.entities.Food;
import br.com.douglasbello.restaurant.model.enums.EnumFoodSize;
import br.com.douglasbello.restaurant.model.enums.EnumFoodType;

import java.util.List;

public interface FoodService {
    List<Food> findAllByFoodType(EnumFoodType type);
    List<Food> findAllSortedByLowestPrice();
    List<Food> findAllSortedByHighestPrice();
    List<Food> findAllBySize(EnumFoodSize size);
}