package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.exceptions.EntityNotFoundException;
import com.odintsov.wallpapers_project.application.interfaces.BaseService;
import com.odintsov.wallpapers_project.application.mappers.common.DtoMapper;
import com.odintsov.wallpapers_project.domain.repositories.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract base implementation of {@link BaseService} providing standard CRUD operations.
 * <p>
 * This class orchestrates the interaction between a {@link CrudRepository} and a
 * {@link DtoMapper}. It handles common business logic such as checking for entity
 * existence before updates/deletions and throwing {@link EntityNotFoundException}
 * when lookups fail.
 *
 * @param <T>                The domain entity type
 * @param <ID>               The identifier type (e.g., Long, UUID)
 * @param <FilterDTO>        The DTO used for filtering search results
 * @param <ListResponse>     The DTO for lightweight list displays
 * @param <DetailedResponse> The DTO for full entity details
 * @param <R>                The specific repository type extending {@link CrudRepository}
 */
public abstract class BaseCrudService<
        T,
        ID,
        FilterDTO,
        ListResponse,
        DetailedResponse,
        R extends CrudRepository<T, ID, FilterDTO>
        > implements BaseService<T, ID, FilterDTO, ListResponse, DetailedResponse> {

    /**
     * The repository used for data persistence.
     */
    protected final R repository;

    /**
     * The mapper used to convert between domain entities and DTOs.
     */
    protected final DtoMapper<T, ListResponse, DetailedResponse> mapper;

    /**
     * Initializes the service with required dependencies.
     *
     * @param repository the repository implementation
     * @param mapper     the DTO mapper implementation
     */
    protected BaseCrudService(R repository, DtoMapper<T, ListResponse, DetailedResponse> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     *
     * @throws EntityNotFoundException if no entity is found with the given id
     */
    @Override
    public DetailedResponse findById(ID id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
        return mapper.toDetailedResponseDto(entity);
    }

    /**
     * Retrieves all entities converted to list-view DTOs.
     *
     * @return a list of ListResponse DTOs
     */
    public List<ListResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toListResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DetailedResponse save(T entity) {
        T savedEntity = repository.save(entity);
        return mapper.toDetailedResponseDto(savedEntity);
    }

    /**
     * Persists multiple entities in a single transaction.
     *
     * @param entities the list of entities to save
     * @return a list of detailed DTOs representing the saved entities
     */
    public List<DetailedResponse> saveAll(List<T> entities) {
        repository.saveAll(entities);

        return entities.stream()
                .map(mapper::toDetailedResponseDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     *
     * @throws EntityNotFoundException if the entity with the specified id does not exist
     */
    @Override
    public DetailedResponse update(ID id, T entity) {
        if (!existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        T updatedEntity = repository.save(entity);
        return mapper.toDetailedResponseDto(updatedEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @throws EntityNotFoundException if the entity with the specified id does not exist
     */
    @Override
    public void delete(ID id) {
        if (!existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        repository.delete(id);
    }

    /**
     * Checks if an entity exists in the repository.
     *
     * @param id the unique identifier to check
     * @return true if the entity exists, false otherwise
     */
    public boolean existsById(ID id) {
        return repository.findById(id).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ListResponse> findAll(FilterDTO filter, Pageable pageable) {
        Page<T> page = repository.filter(filter, pageable);
        return page.map(mapper::toListResponseDto);
    }

    /**
     * Returns the total number of entities available.
     *
     * @return count of entities
     */
    public long count() {
        return repository.count();
    }
}