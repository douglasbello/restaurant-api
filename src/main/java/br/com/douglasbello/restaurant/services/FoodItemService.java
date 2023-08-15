package br.com.douglasbello.restaurant.services;

import br.com.douglasbello.restaurant.model.dtos.FoodItem.FoodItemInputDTO;
import br.com.douglasbello.restaurant.model.entities.FoodItem;

public interface FoodItemService {
    FoodItem createFoodItem(FoodItemInputDTO dto);
}