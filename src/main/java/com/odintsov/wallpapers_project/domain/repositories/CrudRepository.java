package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.application.exceptions.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID, F> {
    Optional<T> findById(ID id) throws EntityNotFoundException;

    List<T> findAll() throws EntityNotFoundException;

    T save(T entity);

    List<T> saveAll(List<T> entities);

    void delete(ID id);

    long count();

    Page<T> findAll(Pageable pageable);

    Page<T> filter(F filter, Pageable pageable);

    void flush();
}