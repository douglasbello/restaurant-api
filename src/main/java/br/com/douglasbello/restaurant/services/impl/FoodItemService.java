package br.com.douglasbello.restaurant.services.impl;

import java.util.List;
import java.util.UUID;

import br.com.douglasbello.restaurant.model.entities.FoodItem;
import br.com.douglasbello.restaurant.services.CommonService;

public class FoodItemService implements CommonService<FoodItem> {
    @Override
    public List<FoodItem> findAll() {
        return null;
    }

    @Override
    public FoodItem findById(UUID id) {
        return null;
    }

    @Override
    public FoodItem save(FoodItem object) {
        return null;
    }

    @Override
    public void delete(UUID id) {
        
    }

    @Override
    public FoodItem update(UUID oldObjectId, FoodItem newObj) {
        return null;
    }

}
