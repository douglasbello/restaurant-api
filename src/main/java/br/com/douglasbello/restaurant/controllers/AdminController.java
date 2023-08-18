package br.com.douglasbello.restaurant.controllers;

import br.com.douglasbello.restaurant.model.dtos.AdminDTO.UsernamePasswordDTO;
import br.com.douglasbello.restaurant.model.dtos.AuthenticationTokenDTO;
import br.com.douglasbello.restaurant.model.dtos.Mapper;
import br.com.douglasbello.restaurant.model.entities.Admin;
import br.com.douglasbello.restaurant.services.impl.AdminServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    private final AdminServiceImpl service;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final Mapper mapper;

    public AdminController(AdminServiceImpl service, Mapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<Admin>> findAll() {
        List<Admin> admins = service.findAll();
        return ResponseEntity.ok(admins);
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<Admin> save(@Valid @RequestBody UsernamePasswordDTO dto) {
        Admin admin = mapper.usernamePasswordDtoToAdmin(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(admin));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationTokenDTO> login(@Valid @RequestBody UsernamePasswordDTO dto) {
        AuthenticationTokenDTO token = service.login(dto);
        return ResponseEntity.ok().body(token);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Admin> update(@PathVariable UUID id, @Valid @RequestBody UsernamePasswordDTO newAdmin) {
        Admin admin = service.updateUsingDTO(id, newAdmin);
        return ResponseEntity.ok().body(admin);
    }
}