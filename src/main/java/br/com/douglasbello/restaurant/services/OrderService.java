package br.com.douglasbello.restaurant.services;

import br.com.douglasbello.restaurant.model.dtos.Order.OrderInputDTO;
import br.com.douglasbello.restaurant.model.entities.Order;

public interface OrderService {
    Order createNewOrder(OrderInputDTO dto);
}