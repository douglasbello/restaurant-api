package br.com.douglasbello.restaurant.controllers;

import br.com.douglasbello.restaurant.model.entities.Admin;
import br.com.douglasbello.restaurant.services.impl.AdminServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/admins")
public class AdminController {
    private final AdminServiceImpl service;

    public AdminController(AdminServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Admin>> findAll() {
        List<Admin> admins = service.findAll();
        return ResponseEntity.ok(admins);
    }

    @PostMapping
    public ResponseEntity<Admin> save(@RequestBody Admin admin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(admin));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Admin> update(@PathVariable UUID id, @RequestBody Admin newAdmin) {
        Admin admin = service.update(id, newAdmin);
        return ResponseEntity.ok().body(admin);
    }
}