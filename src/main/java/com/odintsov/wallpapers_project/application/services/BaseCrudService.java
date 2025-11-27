package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.interfaces.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public abstract class BaseCrudService<T, ID> implements BaseService<T, ID> {

    protected final JpaRepository<T, ID> repository;

    protected BaseCrudService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found with id: " + id));
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    public List<T> saveAll(Iterable<T> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public T update(ID id, T entity) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found with id: " + id));
        return repository.save(entity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {
        repository.deleteAllById(ids);
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        repository.deleteAll(entities);
    }
}
