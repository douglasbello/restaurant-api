package br.com.douglasbello.restaurant.services.impl;

import br.com.douglasbello.restaurant.model.entities.Drink;
import br.com.douglasbello.restaurant.model.enums.EnumDrinkSize;
import br.com.douglasbello.restaurant.model.enums.EnumDrinkType;
import br.com.douglasbello.restaurant.repository.DrinkRepository;
import br.com.douglasbello.restaurant.services.AbstractService;
import br.com.douglasbello.restaurant.services.DrinkService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public class DrinkServiceImpl extends AbstractService<Drink> implements DrinkService {

    private final DrinkRepository repository;

    public DrinkServiceImpl(DrinkRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Drink, UUID> getRepository() {
        return repository;
    }

    @Override
    public void updateData(Drink old, Drink newObj) {
        old.setName(newObj.getName());
        old.setPrice(newObj.getPrice());
        old.setSize(newObj.getSize());
        old.setSodaBrand(newObj.getSodaBrand());
    }

    @Override
    public Drink findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Drink> findAllBySize(EnumDrinkSize size) {
        return repository.findAllBySize(size);
    }

    @Override
    public List<Drink> findAllSortedByLowestPrice() {
        return repository.findAllByPriceAsc();
    }

    @Override
    public List<Drink> findAllSortedByHighestPrice() {
        return repository.findAllByPriceDesc();
    }

    @Override
    public List<Drink> findAllBySodaBrand(EnumDrinkType brand) {
        return repository.findAllBySodaBrand(brand);
    }
}