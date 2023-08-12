package br.com.douglasbello.restaurant.model.entities.interfaces.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.douglasbello.restaurant.model.entities.interfaces.DrinkPriceCalculator;
import br.com.douglasbello.restaurant.model.enums.EnumDrinkSize;

@Service
public class GuaranaPriceCalculator implements DrinkPriceCalculator {

    @Override
    public BigDecimal calculatePrice(EnumDrinkSize size) {
        if (size == EnumDrinkSize.ML600) {
            return new BigDecimal("04.99");
        }
        if (size == EnumDrinkSize.ML1500) {
            return new BigDecimal("07.99");
        }
        if (size == EnumDrinkSize.ML2000) {
            return new BigDecimal("09.99");
        }
        return BigDecimal.ZERO;
    }
}
