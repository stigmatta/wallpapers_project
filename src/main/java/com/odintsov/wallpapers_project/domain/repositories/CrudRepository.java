package com.odintsov.wallpapers_project.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    Optional<T> findById(ID id);

    List<T> findAll();

    T save(T entity);

    List<T> saveAll(List<T> entities);

    void delete(T entity);

    long count();

    Page<T> findAll(Specification<T> spec, Pageable pageable);

    long count(Specification<T> spec);
}
