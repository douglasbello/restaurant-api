package br.com.douglasbello.restaurant.services.impl;

import br.com.douglasbello.restaurant.model.dtos.Mapper;
import br.com.douglasbello.restaurant.model.dtos.Order.OrderInputDTO;
import br.com.douglasbello.restaurant.model.entities.Order;
import br.com.douglasbello.restaurant.repository.OrderRepository;
import br.com.douglasbello.restaurant.services.AbstractService;
import br.com.douglasbello.restaurant.services.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.UUID;

public class OrderServiceImpl extends AbstractService<Order> implements OrderService {

    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Order, UUID> getRepository() {
        return repository;
    }

    @Override
    public void updateData(Order old, Order newObj) {
        old.setCep(newObj.getCep());
        old.setHouseNumber(newObj.getHouseNumber());
        old.setMoment(Instant.now());
        old.setPayment(newObj.getPayment());
    }

    @Override
    public Order createNewOrder(OrderInputDTO dto) {
        Order order = Mapper.dtoToOrder(dto);
        return order;
    }
}