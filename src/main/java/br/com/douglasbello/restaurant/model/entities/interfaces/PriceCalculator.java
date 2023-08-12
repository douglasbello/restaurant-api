package br.com.douglasbello.restaurant.model.entities.interfaces;

import java.math.BigDecimal;

import br.com.douglasbello.restaurant.model.enums.EnumFoodSize;

public interface PriceCalculator {
    BigDecimal calculatePrice(EnumFoodSize size);
}
