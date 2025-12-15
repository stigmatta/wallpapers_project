package com.odintsov.wallpapers_project.application.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T, ID, Filter, ListResponse, DetailedResponse> {
    DetailedResponse findById(ID id);

    Page<ListResponse> findAll(Filter filterDto, Pageable pageable);

    DetailedResponse save(T entity);

    DetailedResponse update(ID id, T entity);

    void deleteById(ID id);

    void delete(T entity);
}
