package br.com.douglasbello.restaurant.services.impl;

import br.com.douglasbello.restaurant.model.dtos.FoodItem.FoodItemDTO;
import br.com.douglasbello.restaurant.model.dtos.Mapper;
import br.com.douglasbello.restaurant.model.entities.FoodItem;
import br.com.douglasbello.restaurant.repository.FoodItemRepository;
import br.com.douglasbello.restaurant.services.AbstractService;
import br.com.douglasbello.restaurant.services.FoodItemService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FoodItemServiceImpl extends AbstractService<FoodItem> implements FoodItemService {

    private final FoodItemRepository repository;

    private final Mapper mapper;

    public FoodItemServiceImpl(FoodItemRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
    public FoodItem createFoodItem(FoodItemDTO dto) {
        FoodItem item = mapper.dtoToFoodItem(dto);
        return item;
    }
}