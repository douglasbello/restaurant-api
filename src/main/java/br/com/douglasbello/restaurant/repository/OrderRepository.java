package br.com.douglasbello.restaurant.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.douglasbello.restaurant.model.entities.Order;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("SELECT o from Order  o ORDER BY o.price")
    List<Order> findAllByPriceAsc();
    @Query("SELECT o from Order  o ORDER BY o.price DESC")
    List<Order> findAllByPriceDesc();
}