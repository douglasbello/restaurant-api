package br.com.douglasbello.restaurant.services.impl;

import br.com.douglasbello.restaurant.model.dtos.DrinkItem.DrinkItemInputDTO;
import br.com.douglasbello.restaurant.model.dtos.Mapper;
import br.com.douglasbello.restaurant.model.entities.DrinkItem;
import br.com.douglasbello.restaurant.repository.DrinkItemRepository;
import br.com.douglasbello.restaurant.services.AbstractService;
import br.com.douglasbello.restaurant.services.CommonService;
import br.com.douglasbello.restaurant.services.DrinkItemService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DrinkItemServiceImpl extends AbstractService<DrinkItem> implements DrinkItemService {

    private final DrinkItemRepository repository;

    public DrinkItemServiceImpl(DrinkItemRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<DrinkItem, UUID> getRepository() {
        return repository;
    }

    @Override
    public DrinkItem createDrinkItem(DrinkItemInputDTO data) {
        DrinkItem item = Mapper.dtoToDrinkItem(data);
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