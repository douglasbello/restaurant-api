package br.com.douglasbello.restaurant.model.entities.interfaces.impl;

import java.math.BigDecimal;

import br.com.douglasbello.restaurant.model.entities.interfaces.PriceCalculator;
import br.com.douglasbello.restaurant.model.enums.EnumFoodSize;

public class DessertPriceCalculator implements PriceCalculator {

    @Override
    public BigDecimal calculatePrice(EnumFoodSize size) {
        if (size == EnumFoodSize.MEDIUM) {
            return new BigDecimal("03.99");
        } 
        if (size == EnumFoodSize.LARGE) {
            return new BigDecimal("08.99");
        }
        return BigDecimal.ZERO;
    }
}
