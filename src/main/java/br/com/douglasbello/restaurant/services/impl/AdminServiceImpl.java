package br.com.douglasbello.restaurant.services.impl;

import java.util.UUID;

import br.com.douglasbello.restaurant.model.entities.Admin;
import br.com.douglasbello.restaurant.repository.AdminRepository;
import br.com.douglasbello.restaurant.services.AbstractService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends AbstractService<Admin> implements UserDetailsService {

    private final AdminRepository repository;

    public AdminServiceImpl(AdminRepository repository) {
        this.repository = repository;
    }

    @Override
    protected JpaRepository<Admin, UUID> getRepository() {
        return repository;
    }

    @Override
    public void updateData(Admin old, Admin newObj) {
        old.setUsername(newObj.getUsername());
        old.setPassword(newObj.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }
}