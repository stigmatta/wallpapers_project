package com.odintsov.wallpapers_project.application.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<T, ID, DTO> {
    T findById(ID id);
    Page<T> findAll(DTO filterDto, Pageable pageable);
    T save(T entity);
    List<T> saveAll(Iterable<T> entities);
    T update(ID id, T entity);
    void deleteById(ID id);
    void delete(T entity);
    void deleteAllById(Iterable<? extends ID> ids);
    void deleteAll(Iterable<? extends T> entities);
}
