package br.com.douglasbello.restaurant.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.douglasbello.restaurant.model.dtos.AuthenticationTokenDTO;
import br.com.douglasbello.restaurant.model.dtos.Mapper;
import br.com.douglasbello.restaurant.model.dtos.AdminDTO.AdminResponseDTO;
import br.com.douglasbello.restaurant.model.dtos.AdminDTO.UsernamePasswordDTO;
import br.com.douglasbello.restaurant.model.entities.Admin;
import br.com.douglasbello.restaurant.services.impl.AdminServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping(value = "/admin", produces = {"application/json"})
@Tag(name = "Admin")
public class AdminController {
    private final AdminServiceImpl service;
    private final Mapper mapper;


    public AdminController(AdminServiceImpl service, Mapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Return all the admins in the database. Requires token.", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns list of all admins registered in the database."),
            @ApiResponse(responseCode = "403", description = "The token was not valid. Unauthorized.")})
    @GetMapping
    public ResponseEntity<List<AdminResponseDTO>> findAll() {
        List<Admin> data = service.findAll();
        List<AdminResponseDTO> response = data.stream().map(AdminResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Receives a UsernamePasswordDTO, converts it into Admin, then creates new admin account. Requires token", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New admin created successfully."),
            @ApiResponse(responseCode = "400", description = "DTO was not validated successfully."),
            @ApiResponse(responseCode = "403", description = "The token was not valid. Unauthorized.")})
    @PostMapping(value = "/sign-up")
    public ResponseEntity<AdminResponseDTO> save(@Valid @RequestBody UsernamePasswordDTO dto) {
        Admin admin = mapper.usernamePasswordDtoToAdmin(dto);
        admin = service.save(admin);
        AdminResponseDTO response = new AdminResponseDTO(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Receives UsernamePasswordDTO, verifies if exists a admin with the same username and password as the DTO. Requires token.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin login successfully, returns a token."),
            @ApiResponse(responseCode = "400", description = "DTO was not validated successfully."),
            @ApiResponse(responseCode = "403", description = "The token was not valid. Unauthorized.")})
    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationTokenDTO> login(@Valid @RequestBody UsernamePasswordDTO dto) {
        AuthenticationTokenDTO token = service.login(dto);
        return ResponseEntity.ok().body(token);
    }

    @Operation(summary = "Receives a id, find the Admin by id then deletes it. Requires token.", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Admin found and deleted successfully."),
            @ApiResponse(responseCode = "403", description = "The token was not valid. Unauthorized."),
            @ApiResponse(responseCode = "404", description = "Admin not found.")})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Receives id and UsernamePasswordDTO, finds admin by id, updates it and save it again in the database. Requires token.", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin found and updated successfully."),
            @ApiResponse(responseCode = "400", description = "DTO was not validated successfully."),
            @ApiResponse(responseCode = "403", description = "The token was not valid. Unauthorized."),
            @ApiResponse(responseCode = "404", description = "Admin not found.")})
    @PutMapping(value = "/{id}")
    public ResponseEntity<AdminResponseDTO> update(@PathVariable UUID id, @Valid @RequestBody UsernamePasswordDTO newAdmin) {
        Admin admin = service.updateUsingDTO(id, newAdmin);
        AdminResponseDTO response = new AdminResponseDTO(admin);
        return ResponseEntity.ok().body(response);
    }
}
