package br.com.douglasbello.restaurant.services.impl;

import java.util.List;
import java.util.UUID;

import br.com.douglasbello.restaurant.model.entities.Drink;
import br.com.douglasbello.restaurant.services.CommonService;

public class DrinkService implements CommonService<Drink> {
    @Override
    public List<Drink> findAll() {
        return null;
    }

    @Override
    public Drink findById(UUID id) {
        return null;
    }

    @Override
    public Drink save(Drink object) {
        return null;
    }

    @Override
    public void delete(UUID id) {
        
    }

    @Override
    public void updateData(Drink old, Drink newObj) {

    }

    @Override
    public Drink update(UUID oldObjectId, Drink newObj) {
        return null;
    }

}
