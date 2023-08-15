package br.com.douglasbello.restaurant.services.impl;

import java.util.List;
import java.util.UUID;

import br.com.douglasbello.restaurant.model.entities.Order;
import br.com.douglasbello.restaurant.services.CommonService;

public class OrderService implements CommonService<Order> {
    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order findById(UUID id) {
        return null;
    }

    @Override
    public Order save(Order object) {
        return null;
    }

    @Override
    public Order update(UUID oldObjectId, Order newObj) {
        return null;
    }

    @Override
    public void delete(UUID id) {
        
    }

    @Override
    public void updateData(Order old, Order newObj) {

    }
}
