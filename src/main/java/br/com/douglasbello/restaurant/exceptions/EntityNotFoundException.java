package br.com.douglasbello.restaurant.exceptions;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(UUID id) {
        super("Entity not found with id: " + id);
    }
}