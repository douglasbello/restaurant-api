package br.com.douglasbello.restaurant.services;

import br.com.douglasbello.restaurant.model.entities.Drink;
import br.com.douglasbello.restaurant.model.enums.EnumDrinkSize;
import br.com.douglasbello.restaurant.model.enums.EnumDrinkType;

import java.util.List;

public interface DrinkService {
    Drink findByName(String name);
    List<Drink> findAllBySize(EnumDrinkSize size);
    List<Drink> findAllSortedByLowestPrice();
    List<Drink> findAllSortedByHighestPrice();
    List<Drink> findAllBySodaBrand(EnumDrinkType brand);
}