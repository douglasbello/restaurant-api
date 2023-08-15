package br.com.douglasbello.restaurant.services.impl;

import br.com.douglasbello.restaurant.model.dtos.FoodItem.FoodItemInputDTO;
import br.com.douglasbello.restaurant.model.dtos.Mapper;
import br.com.douglasbello.restaurant.model.entities.FoodItem;
import br.com.douglasbello.restaurant.repository.FoodItemRepository;
import br.com.douglasbello.restaurant.services.AbstractService;
import br.com.douglasbello.restaurant.services.FoodItemService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class FoodItemServiceImpl extends AbstractService<FoodItem> implements FoodItemService {

    private final FoodItemRepository repository;

    public FoodItemServiceImpl(FoodItemRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<FoodItem, UUID> getRepository() {
        return repository;
    }

    @Override
    public void updateData(FoodItem old, FoodItem newObj) {
        old.setQuantity(newObj.getQuantity());
        old.setFood(newObj.getFood());
        old.setOrder(newObj.getOrder());
        old.setPrice(newObj.getPrice());
    }

    @Override
    public FoodItem createFoodItem(FoodItemInputDTO dto) {
        FoodItem item = Mapper.dtoToFoodItem(dto);
        return item;
    }
}