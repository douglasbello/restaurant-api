package br.com.douglasbello.restaurant.services;

import java.util.List;
import java.util.UUID;

/* this class represents commons methods that all services in this API must implement */
public interface CommonService<T> {
    List<T> findAll();
    T findById(UUID id);
    T save(T object);
    T update(UUID oldObjectId, T newObj);
    void delete(UUID id);
}
