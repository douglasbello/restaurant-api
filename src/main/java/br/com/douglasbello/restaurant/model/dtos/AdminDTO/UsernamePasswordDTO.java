package br.com.douglasbello.restaurant.model.dtos.AdminDTO;

import jakarta.validation.constraints.NotBlank;

public record UsernamePasswordDTO(@NotBlank(message = "Username cannot be blank or null.") String username,
                                  @NotBlank(message = "Password cannot be blank or null.") String password) {
}