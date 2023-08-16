package br.com.douglasbello.restaurant.services.impl;

import br.com.douglasbello.restaurant.model.dtos.Mapper;
import br.com.douglasbello.restaurant.model.dtos.Order.OrderInputDTO;
import br.com.douglasbello.restaurant.model.entities.Order;
import br.com.douglasbello.restaurant.repository.OrderRepository;
import br.com.douglasbello.restaurant.services.AbstractService;
import br.com.douglasbello.restaurant.services.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl extends AbstractService<Order> implements OrderService {

    private final OrderRepository repository;

    private final Mapper mapper;

    public OrderServiceImpl(OrderRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
        Order order = mapper.dtoToOrder(dto);
        return order;
    }

    @Override
    public List<Order> findAllSortedByLowestPrice() {
        List<Order> data = repository.findAllByPriceAsc();
        return data;
    }

    @Override
    public List<Order> findAllSortedByHighestPrice() {
        List<Order> data = repository.findAllByPriceDesc();
        return data;
    }
}