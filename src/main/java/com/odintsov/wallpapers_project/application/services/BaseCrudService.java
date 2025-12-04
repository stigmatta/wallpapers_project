package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.exceptions.EntityNotFoundException;
import com.odintsov.wallpapers_project.application.interfaces.BaseService;
import com.odintsov.wallpapers_project.domain.repositories.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Base abstract CRUD service that provides common operations for
 * interacting with a JPA repository.
 *
 * <p>This class implements the {@link BaseService} interface and delegates
 * all CRUD operations to the injected {@link JpaRepository} instance.
 * It is intended to be extended by concrete service classes for specific entities.</p>
 *
 * @param <T>  the type of the entity
 * @param <ID> the type of the entity identifier
 * @param <FilterDTO> the type of the filter DTO for queries
 * @param <ListResponse> the type of the response DTO for list operations
 * @param <DetailedResponse> the type of the response DTO for single entity operations
 * @param <R> the repository type
 */
public abstract class BaseCrudService<
        T,
        ID,
        FilterDTO,
        ListResponse,
        DetailedResponse,
        R extends BaseRepository<T, ID> & JpaSpecificationExecutor<T>>
        implements BaseService<T, ID, FilterDTO, ListResponse, DetailedResponse> {

    /**
     * The JPA repository used for performing CRUD operations.
     */
    protected final R repository;

    /**
     * Constructs a new BaseCrudService with the given JPA repository.
     *
     * @param repository the JPA repository used by this service
     */
    protected BaseCrudService(R repository) {
        this.repository = repository;
    }

    /**
     * Builds a Specification from a filter DTO.
     * Must be implemented by each service that wants to support filtering.
     *
     * @param filter a filter DTO
     * @return Specification<T> representing the query conditions
     */
    protected abstract Specification<T> buildSpecification(FilterDTO filter);

    /**
     * Converts an entity to a list response DTO.
     * Must be implemented by each service.
     *
     * @param entity the entity to convert
     * @return the list response DTO
     */
    protected abstract ListResponse toListResponseDto(T entity);

    /**
     * Converts an entity to a detailed response DTO.
     * Must be implemented by each service.
     *
     * @param entity the entity to convert
     * @return the detailed response DTO
     */
    protected abstract DetailedResponse toDetailedResponseDto(T entity);

    /**
     * Retrieves all entities that match the given specification, with pagination.
     * Returns list response DTOs instead of entities.
     *
     * @param filterDto filter DTO (required, cannot be null)
     * @param pageable pagination and sorting information
     * @return paged list of list response DTOs
     * @throws IllegalArgumentException if filterDto is null
     */
    @Override
    public Page<ListResponse> findAll(FilterDTO filterDto, Pageable pageable) {
        Specification<T> spec = buildSpecification(filterDto);
        Page<T> entities = repository.findAll(spec, pageable);
        return entities.map(this::toListResponseDto);
    }

    /**
     * Finds an entity by its ID and returns it as a detailed response DTO.
     *
     * @param id the ID of the entity to retrieve
     * @return the detailed response DTO
     * @throws EntityNotFoundException if the entity is not found
     */
    @Override
    public DetailedResponse findById(ID id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        return toDetailedResponseDto(entity);
    }

    /**
     * Saves a new entity or updates an existing one and returns it as a detailed response DTO.
     *
     * @param entity the entity to save
     * @return the detailed response DTO of the saved entity
     */
    @Override
    public DetailedResponse save(T entity) {
        T savedEntity = repository.save(entity);
        return toDetailedResponseDto(savedEntity);
    }

    /**
     * Saves all provided entities.
     *
     * @param entities a collection of entities to save
     * @return the list of saved entities
     */
    @Override
    public List<T> saveAll(Iterable<T> entities) {
        return repository.saveAll(entities);
    }

    /**
     * Updates an existing entity with the given ID and returns it as a detailed response DTO.
     *
     * <p>This method checks whether the entity with the given ID exists
     * before saving the updated entity.</p>
     *
     * @param id     the ID of the entity to update
     * @param entity the updated entity data
     * @return the detailed response DTO of the updated entity
     * @throws EntityNotFoundException if the entity with the given ID is not found
     */
    @Override
    public DetailedResponse update(ID id, T entity) {
        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        T updatedEntity = repository.save(entity);
        return toDetailedResponseDto(updatedEntity);
    }

    /**
     * Deletes an entity by its ID.
     *
     * @param id the ID of the entity to delete
     */
    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    /**
     * Deletes the provided entity.
     *
     * @param entity the entity to delete
     */
    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    /**
     * Deletes all entities with the given IDs.
     *
     * @param ids an iterable of IDs whose entities should be deleted
     */
    @Override
    public void deleteAllById(Iterable<? extends ID> ids) {
        repository.deleteAllById(ids);
    }

    /**
     * Deletes all provided entities.
     *
     * @param entities an iterable of entities to delete
     */
    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        repository.deleteAll(entities);
    }
}