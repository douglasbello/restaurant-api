package br.com.douglasbello.restaurant.services.impl;

import br.com.douglasbello.restaurant.model.dtos.DrinkItem.DrinkItemDTO;
import br.com.douglasbello.restaurant.model.dtos.Mapper;
import br.com.douglasbello.restaurant.model.entities.DrinkItem;
import br.com.douglasbello.restaurant.repository.DrinkItemRepository;
import br.com.douglasbello.restaurant.services.AbstractService;
import br.com.douglasbello.restaurant.services.DrinkItemService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DrinkItemServiceImpl extends AbstractService<DrinkItem> implements DrinkItemService {

    private final DrinkItemRepository repository;

    private final Mapper mapper;

    public DrinkItemServiceImpl(DrinkItemRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    protected JpaRepository<DrinkItem, UUID> getRepository() {
        return repository;
    }

    @Override
    public DrinkItem createDrinkItem(DrinkItemDTO data) {
        DrinkItem item = mapper.dtoToDrinkItem(data);
        return item;
    }

    @Override
    public void updateData(DrinkItem old, DrinkItem newObj) {
        old.setDrink(newObj.getDrink());
        old.setQuantity(newObj.getQuantity());
        old.setOrder(newObj.getOrder());
        old.setPrice(newObj.getPrice());
    }
}