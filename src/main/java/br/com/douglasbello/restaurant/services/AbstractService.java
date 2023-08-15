package br.com.douglasbello.restaurant.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public abstract class AbstractService<T> implements CommonService<T> {

    protected abstract JpaRepository<T, UUID> getRepository();

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public T findById(UUID id) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public T save(T obj) {
        return getRepository().save(obj);
    }

    @Override
    public T update(UUID oldObjectId, T newObj) {
        return getRepository().save(newObj);
    }

    @Override
    public void delete(UUID id) {
        getRepository().deleteById(id);
    }
}