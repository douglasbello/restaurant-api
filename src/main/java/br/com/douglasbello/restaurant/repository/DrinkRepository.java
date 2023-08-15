package br.com.douglasbello.restaurant.repository;

import java.util.List;
import java.util.UUID;

import br.com.douglasbello.restaurant.model.enums.EnumDrinkSize;
import br.com.douglasbello.restaurant.model.enums.EnumDrinkType;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.douglasbello.restaurant.model.entities.Drink;
import org.springframework.data.jpa.repository.Query;

public interface DrinkRepository extends JpaRepository<Drink, UUID> {
    Drink findByName(String name);
    List<Drink> findAllBySodaBrand(EnumDrinkType sodaBrand);
    List<Drink> findAllBySize(EnumDrinkSize size);
    @Query("SELECT d from Drink d ORDER BY d.price")
    List<Drink> findAllByPriceAsc();
    @Query("SELECT d from Drink d ORDER BY d.price DESC")
    List<Drink> findAllByPriceDesc();
}