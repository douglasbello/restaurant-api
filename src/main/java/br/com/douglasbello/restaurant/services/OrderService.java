package br.com.douglasbello.restaurant.services;

import br.com.douglasbello.restaurant.model.dtos.Order.OrderInputDTO;
import br.com.douglasbello.restaurant.model.entities.Order;

import java.util.List;

public interface OrderService {
    Order createNewOrder(OrderInputDTO dto);
    List<Order> findAllSortedByLowestPrice();
    List<Order> findAllSortedByHighestPrice();
}