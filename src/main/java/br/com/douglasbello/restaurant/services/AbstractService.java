package br.com.douglasbello.restaurant.services;

import br.com.douglasbello.restaurant.exceptions.EntityNotFoundException;
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
        return getRepository().findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public T save(T obj) {
        return getRepository().save(obj);
    }

    /* the method updateData() is not implemented in this class, because each class has different attributes and different updateData implementations. */
    @Override
    public T update(UUID oldObjectId, T newObj) {
        T oldObject = getRepository().findById(oldObjectId).orElseThrow(() -> new EntityNotFoundException(oldObjectId));
        updateData(oldObject, newObj);
        return getRepository().save(oldObject);
    }

    @Override
    public void delete(UUID id) {
        getRepository().delete(getRepository().findById(id).orElseThrow(() -> new EntityNotFoundException(id)));
    }
}
