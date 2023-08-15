package br.com.douglasbello.restaurant.services.impl;

import java.util.List;
import java.util.UUID;

import br.com.douglasbello.restaurant.model.entities.Admin;
import br.com.douglasbello.restaurant.repository.AdminRepository;
import br.com.douglasbello.restaurant.services.CommonService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements CommonService<Admin> {
    private final AdminRepository repository;

    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Admin> findAll() {
        return repository.findAll();
    }

    @Override
    public Admin findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Admin save(Admin admin) {
        return repository.save(admin);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);   
    }

    @Override
    public Admin update(UUID oldObjectId, Admin newObj) {
        try {
            Admin oldObject = findById(oldObjectId);
            updateData(oldObject, newObj);
            return repository.save(oldObject);
        } catch (EntityNotFoundException ex) {
            throw new RuntimeException();
        } 
    }

    @Override
    public void updateData(Admin old, Admin newObj) {
        old.setUsername(newObj.getUsername());
        old.setPassword(newObj.getPassword());
    }

}
