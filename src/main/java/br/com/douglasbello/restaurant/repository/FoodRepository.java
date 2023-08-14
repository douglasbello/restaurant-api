package br.com.douglasbello.restaurant.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.douglasbello.restaurant.model.entities.Food;

public interface FoodRepository extends JpaRepository<Food, UUID> {
}
