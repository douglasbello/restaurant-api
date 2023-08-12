package br.com.douglasbello.restaurant.model.entities.interfaces.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.douglasbello.restaurant.model.entities.interfaces.PriceCalculator;
import br.com.douglasbello.restaurant.model.enums.EnumFoodSize;

@Service
public class HotdogPriceCalculator implements PriceCalculator {
    @Override
    public BigDecimal calculatePrice(EnumFoodSize size) {
        if (size == EnumFoodSize.MEDIUM) {
            return new BigDecimal("0.99");
        }
        if (size == EnumFoodSize.LARGE) {
            return new BigDecimal("01.99");
        }
        return BigDecimal.ZERO;
    }
}
