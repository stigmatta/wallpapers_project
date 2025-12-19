package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.exceptions.EntityNotFoundException;
import com.odintsov.wallpapers_project.application.interfaces.BaseService;
import com.odintsov.wallpapers_project.application.mappers.common.DtoMapper;
import com.odintsov.wallpapers_project.domain.repositories.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseCrudService<
        T,
        ID,
        FilterDTO,
        ListResponse,
        DetailedResponse,
        R extends CrudRepository<T, ID, FilterDTO>
        > implements BaseService<T, ID, FilterDTO, ListResponse, DetailedResponse> {

    protected final R repository;
    protected final DtoMapper<T, ListResponse, DetailedResponse> mapper;

    protected BaseCrudService(R repository, DtoMapper<T, ListResponse, DetailedResponse> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DetailedResponse findById(ID id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        return mapper.toDetailedResponseDto(entity);
    }

    public List<ListResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toListResponseDto)
                .collect(Collectors.toList());
    }

    public DetailedResponse save(T entity) {
        T savedEntity = repository.save(entity);
        return mapper.toDetailedResponseDto(savedEntity);
    }

    public List<DetailedResponse> saveAll(List<T> entities) {
        List<T> savedEntities = repository.saveAll(entities);
        return savedEntities.stream()
                .map(mapper::toDetailedResponseDto)
                .collect(Collectors.toList());
    }

    public DetailedResponse update(ID id, T entity) {
        if (!existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        T updatedEntity = repository.save(entity);
        return mapper.toDetailedResponseDto(updatedEntity);
    }

    public void delete(ID id) {
        if (!existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        repository.delete(id);
    }

    public boolean existsById(ID id) {
        return repository.findById(id).isPresent();
    }

    /**
     * Тепер сервіс просто делегує фільтрацію репозиторію.
     * Репозиторій (Adapter) сам вирішить, як застосувати FilterDTO:
     * через Specification (для Oracle) чи через Query (для Firebase).
     */
    public Page<ListResponse> findAll(FilterDTO filter, Pageable pageable) {
        Page<T> page = repository.findAll(pageable);
        return page.map(mapper::toListResponseDto);
    }

    public long count() {
        return repository.count();
    }
}