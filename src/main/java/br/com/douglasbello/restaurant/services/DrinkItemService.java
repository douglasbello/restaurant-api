package br.com.douglasbello.restaurant.services;

import br.com.douglasbello.restaurant.model.dtos.DrinkItem.DrinkItemInputDTO;
import br.com.douglasbello.restaurant.model.entities.DrinkItem;

public interface DrinkItemService {
    DrinkItem createDrinkItem(DrinkItemInputDTO data);
}