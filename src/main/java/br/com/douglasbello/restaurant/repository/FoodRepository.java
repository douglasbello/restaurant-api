package br.com.douglasbello.restaurant.repository;

import java.util.List;
import java.util.UUID;

import br.com.douglasbello.restaurant.model.enums.EnumFoodSize;
import br.com.douglasbello.restaurant.model.enums.EnumFoodType;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.douglasbello.restaurant.model.entities.Food;
import org.springframework.data.jpa.repository.Query;

public interface FoodRepository extends JpaRepository<Food, UUID> {
    Food findByName(String name);
    List<Food> findAllByType(EnumFoodType type);
    @Query("SELECT f from Food f ORDER BY f.price")
    List<Food> findAllByPriceAsc();
    @Query("SELECT f from Food f ORDER BY f.price DESC")
    List<Food> findAllByPriceDesc();
    List<Food> findAllBySize(EnumFoodSize size);
}
