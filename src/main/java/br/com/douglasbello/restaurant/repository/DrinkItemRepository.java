package br.com.douglasbello.restaurant.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.douglasbello.restaurant.model.entities.DrinkItem;

public interface DrinkItemRepository extends JpaRepository<DrinkItem, UUID> {
}
