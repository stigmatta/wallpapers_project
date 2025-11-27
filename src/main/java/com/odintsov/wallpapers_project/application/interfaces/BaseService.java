package com.odintsov.wallpapers_project.application.interfaces;

import java.util.List;

public interface BaseService<T, ID> {
    List<T> findAll();
    T findById(ID id);
    T save(T entity);
    List<T> saveAll(Iterable<T> entities);
    T update(ID id, T entity);
    void deleteById(ID id);
    void delete(T entity);
    void deleteAllById(Iterable<? extends ID> ids);
    void deleteAll(Iterable<? extends T> entities);
}
