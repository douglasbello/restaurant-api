package br.com.douglasbello.restaurant.services.impl;

import java.util.List;
import java.util.UUID;

import br.com.douglasbello.restaurant.model.entities.Admin;
import br.com.douglasbello.restaurant.repository.AdminRepository;
import br.com.douglasbello.restaurant.services.AbstractService;
import br.com.douglasbello.restaurant.services.CommonService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends AbstractService<Admin> {

    private final AdminRepository repository;

    public AdminService(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Admin, UUID> getRepository() {
        return repository;
    }
}
