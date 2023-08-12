package br.com.douglasbello.restaurant.model.entities.interfaces;

import java.math.BigDecimal;

import br.com.douglasbello.restaurant.model.enums.EnumDrinkSize;

public interface DrinkPriceCalculator {
    BigDecimal calculatePrice(EnumDrinkSize size);
}
