package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.application.exceptions.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * A generic base repository interface providing standard CRUD operations
 * and advanced filtering capabilities.
 * * @param <T>  The entity type managed by the repository.
 *
 * @param <ID> The type of the entity's identifier (usually String or UUID).
 * @param <F>  The filter DTO type used for faceted search and criteria.
 */
public interface CrudRepository<T, ID, F> {

    /**
     * Retrieves an entity by its identifier.
     *
     * @return an Optional containing the entity, or empty if not found.
     * @throws EntityNotFoundException if specific business logic requires
     *                                 the existence of the entity.
     */
    Optional<T> findById(ID id) throws EntityNotFoundException;

    /**
     * Returns all instances of the type.
     */
    List<T> findAll() throws EntityNotFoundException;

    /**
     * Saves a given entity. Use the returned instance for further operations.
     */
    <S extends T> S save(S entity);

    /**
     * Saves all given entities in a batch.
     */
    void saveAll(List<? extends T> entities);

    /**
     * Deletes the entity with the given id.
     */
    void delete(ID id);

    /**
     * Returns the total number of entities available.
     */
    long count();

    /**
     * Returns a {@link Page} of entities meeting the paging restriction.
     */
    Page<T> findAll(Pageable pageable);

    /**
     * Filters entities based on a criteria object.
     *
     * @param filter   the search criteria DTO.
     * @param pageable the pagination information.
     * @return a page of matching entities.
     */
    Page<T> filter(F filter, Pageable pageable);

    /**
     * Synchronizes the persistence context with the underlying database.
     */
    void flush();
}