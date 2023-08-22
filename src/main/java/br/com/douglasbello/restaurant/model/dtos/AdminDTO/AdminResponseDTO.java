package br.com.douglasbello.restaurant.model.dtos.AdminDTO;

import java.util.UUID;

import br.com.douglasbello.restaurant.model.entities.Admin;

public class AdminResponseDTO {
    private UUID id;
    private String username;
    private String password;


    public AdminResponseDTO() {}

    public AdminResponseDTO(Admin admin) {
        this.id = admin.getId();
        this.username = admin.getUsername();
        this.password = admin.getPassword();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
