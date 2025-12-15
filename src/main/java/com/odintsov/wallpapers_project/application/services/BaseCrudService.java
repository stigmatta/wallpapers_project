package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.exceptions.EntityNotFoundException;
import com.odintsov.wallpapers_project.application.interfaces.BaseService;
import com.odintsov.wallpapers_project.application.mappers.common.DtoMapper;
import com.odintsov.wallpapers_project.domain.repositories.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Base CRUD service that works with domain CrudRepository.
 * Provides common implementations that can be overridden when needed.
 */
public abstract class BaseCrudService<
        T,
        ID,
        FilterDTO,
        ListResponse,
        DetailedResponse,
        R extends CrudRepository<T, ID>
        > implements BaseService<T, ID, FilterDTO, ListResponse, DetailedResponse> {

    protected final R repository;
    protected final DtoMapper<T, ListResponse, DetailedResponse> mapper;

    protected BaseCrudService(R repository, DtoMapper<T, ListResponse, DetailedResponse> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Build JPA Specification from filter DTO.
     * Implement this to define your filtering logic.
     * <p>
     * If you don't need filtering, just return an empty specification:
     * return (root, query, cb) -> cb.conjunction();
     */
    protected abstract Specification<T> buildSpecification(FilterDTO filter);


    /**
     * Find entity by ID and return as DetailedResponse DTO.
     * Override this method if you need custom find logic.
     */
    public DetailedResponse findById(ID id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        return mapper.toDetailedResponseDto(entity);
    }

    /**
     * Find all entities and return as list of ListResponse DTOs.
     * Override this method if you need custom findAll logic.
     */
    public List<ListResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toListResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Save a new entity and return as DetailedResponse DTO.
     * Override this method if you need validation or pre-save logic.
     */
    public DetailedResponse save(T entity) {
        T savedEntity = repository.save(entity);
        return mapper.toDetailedResponseDto(savedEntity);
    }

    /**
     * Save multiple entities and return as list of DetailedResponse DTOs.
     * Override this method if you need batch validation logic.
     */
    public List<DetailedResponse> saveAll(List<T> entities) {
        List<T> savedEntities = repository.saveAll(entities);
        return savedEntities.stream()
                .map(mapper::toDetailedResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * Update an existing entity and return as DetailedResponse DTO.
     * Override this method if you need custom update logic.
     */
    public DetailedResponse update(ID id, T entity) {
        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));

        T updatedEntity = repository.save(entity);
        return mapper.toDetailedResponseDto(updatedEntity);
    }

    /**
     * Delete entity by ID.
     * Override this method if you need pre-delete checks or soft delete.
     */
    public void deleteById(ID id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        repository.delete(entity);
    }

    /**
     * Delete entity directly.
     * Override this method if needed.
     */
    public void delete(T entity) {
        repository.delete(entity);
    }

    /**
     * Check if entity exists by ID.
     * Override this method if you have a more efficient existence check.
     */
    public boolean existsById(ID id) {
        return repository.findById(id).isPresent();
    }

    /**
     * Find all with pagination and filtering.
     * Uses buildSpecification() to apply filters.
     */
    public Page<ListResponse> findAll(FilterDTO filter, Pageable pageable) {
        Specification<T> spec = buildSpecification(filter);
        Page<T> page = repository.findAll(spec, pageable);
        return page.map(mapper::toListResponseDto);
    }

    /**
     * Count entities matching filter.
     */
    public long count(FilterDTO filter) {
        Specification<T> spec = buildSpecification(filter);
        return repository.count(spec);
    }
}