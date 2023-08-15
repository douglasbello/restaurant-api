package br.com.douglasbello.restaurant.services.impl;

import java.util.List;
import java.util.UUID;

import br.com.douglasbello.restaurant.model.entities.Food;
import br.com.douglasbello.restaurant.services.CommonService;

public class FoodService implements CommonService<Food> {

    @Override
    public List<Food> findAll() {
        return null;
    }

    @Override
    public Food findById(UUID id) {
        return null;
    }

    @Override
    public Food save(Food object) {
        return null;
    }

    @Override
    public void delete(UUID id) {
        
    }

    @Override
    public void updateData(Food old, Food newObj) {

    }

    @Override
    public Food update(UUID oldObjectId, Food newObj) {
        return null;
    }
}
