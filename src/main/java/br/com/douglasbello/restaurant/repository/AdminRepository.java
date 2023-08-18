package br.com.douglasbello.restaurant.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.douglasbello.restaurant.model.entities.Admin;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    UserDetails findByUsername(String username);
}
