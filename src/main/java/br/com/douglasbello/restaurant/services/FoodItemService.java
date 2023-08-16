package br.com.douglasbello.restaurant.services;

import br.com.douglasbello.restaurant.model.dtos.FoodItem.FoodItemDTO;
import br.com.douglasbello.restaurant.model.entities.FoodItem;

public interface FoodItemService {
    FoodItem createFoodItem(FoodItemDTO dto);
}