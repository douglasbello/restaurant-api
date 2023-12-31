package br.com.douglasbello.restaurant.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import br.com.douglasbello.restaurant.model.dtos.Food.FoodInputDTO;
import br.com.douglasbello.restaurant.model.entities.Food;
import br.com.douglasbello.restaurant.model.enums.EnumFoodSize;
import br.com.douglasbello.restaurant.model.enums.EnumFoodType;
import br.com.douglasbello.restaurant.repository.FoodRepository;
import br.com.douglasbello.restaurant.services.AbstractService;
import br.com.douglasbello.restaurant.services.FoodService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl extends AbstractService<Food> implements FoodService {

    private final FoodRepository repository;

    public FoodServiceImpl(FoodRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Food, UUID> getRepository() {
        return repository;
    }

    public void updateDataUsingDTO(Food old, FoodInputDTO newObj) {
        old.setName(newObj.getName());
        old.setDescription(newObj.getDescription());
        old.setPrice(BigDecimal.ZERO);
        System.out.println(old.getPrice());
        old.setPrice(newObj.getPrice());
        System.out.println(old.getPrice());
        old.setSize(newObj.getSize());
        old.setType(newObj.getType());
    }

    @Override
    public List<Food> findAllByFoodType(EnumFoodType type) {
        return repository.findAllByType(type);
    }

    @Override
    public List<Food> findAllSortedByLowestPrice() {
        return repository.findAllByPriceAsc();
    }

    @Override
    public List<Food> findAllSortedByHighestPrice() {
        return repository.findAllByPriceDesc();
    }

    @Override
    public List<Food> findAllBySize(EnumFoodSize size) {
        return repository.findAllBySize(size);
    }

    @Override
    public void updateData(Food old, Food newObj) {

    }

    public Food updateUsingDTO(UUID id, FoodInputDTO dto) {
        Food oldObject  = findById(id);
        updateDataUsingDTO(oldObject, dto);
        return getRepository().save(oldObject);
    }
}
