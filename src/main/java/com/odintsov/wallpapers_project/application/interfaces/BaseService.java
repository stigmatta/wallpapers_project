package com.odintsov.wallpapers_project.application.interfaces;

import com.odintsov.wallpapers_project.application.exceptions.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * A generic base interface for service layer operations.
 *
 * @param <T>                The Domain Entity type
 * @param <ID>               The type of the Entity identifier (e.g., Long, UUID)
 * @param <Filter>           The DTO type used for filtering search results
 * @param <ListResponse>     The DTO type returned for paginated list views
 * @param <DetailedResponse> The DTO type returned for single-entity detailed views
 */
public interface BaseService<T, ID, Filter, ListResponse, DetailedResponse> {

    /**
     * Retrieves a single entity by its unique identifier.
     *
     * @param id the identifier of the entity to find
     * @return the detailed DTO representation of the entity
     * @throws EntityNotFoundException if no entity exists with the given ID
     */
    DetailedResponse findById(ID id);

    /**
     * Retrieves a paginated list of entities filtered by the provided criteria.
     *
     * @param filterDto the criteria used to filter the results
     * @param pageable  pagination and sorting information
     * @return a page of list-view DTOs
     */
    Page<ListResponse> findAll(Filter filterDto, Pageable pageable);

    /**
     * Persists a new entity in the system.
     *
     * @param entity the entity to be saved
     * @return the detailed DTO representation of the saved entity
     */
    DetailedResponse save(T entity);

    /**
     * Updates an existing entity identified by the given ID.
     *
     * @param id     the identifier of the entity to update
     * @param entity the updated entity data
     * @return the detailed DTO representation of the updated entity
     * @throws EntityNotFoundException if the entity to update does not exist
     */
    DetailedResponse update(ID id, T entity);

    /**
     * Deletes an entity from the system by its identifier.
     *
     * @param id the identifier of the entity to delete
     * @throws EntityNotFoundException if the entity to delete does not exist
     */
    void delete(ID id);
}