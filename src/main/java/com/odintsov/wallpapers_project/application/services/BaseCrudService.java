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
 */
public abstract class BaseCrudService<
        T,
        ID,
        DTO,
        R extends BaseRepository<T, ID> & JpaSpecificationExecutor<T>>
        implements BaseService<T, ID, DTO> {

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
    protected abstract Specification<T> buildSpecification(DTO filter);


    /**
     * Retrieves all entities that match the given specification, with pagination.
     *
     * @param filterDto     JPA Specification (filters)
     * @param pageable pagination and sorting information
     * @return paged list of entities
     */
    public Page<T> findAll(DTO filterDto, Pageable pageable) {
        Specification<T> spec = buildSpecification(filterDto);
        return repository.findAll(spec, pageable);
    }


    /**
     * Finds an entity by its ID.
     *
     * @param id the ID of the entity to retrieve
     * @return the found entity
     * @throws RuntimeException if the entity is not found
     */
    @Override
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    /**
     * Saves a new entity or updates an existing one.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    @Override
    public T save(T entity) {
        return repository.save(entity);
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
     * Updates an existing entity with the given ID.
     *
     * <p>This method checks whether the entity with the given ID exists
     * before saving the updated entity.</p>
     *
     * @param id     the ID of the entity to update
     * @param entity the updated entity data
     * @return the saved entity
     * @throws RuntimeException if the entity with the given ID is not found
     */
    @Override
    public T update(ID id, T entity) {
        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        return repository.save(entity);
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
