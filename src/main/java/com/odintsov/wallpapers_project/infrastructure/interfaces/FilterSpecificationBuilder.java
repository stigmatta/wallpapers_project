package com.odintsov.wallpapers_project.infrastructure.interfaces;

import org.springframework.data.jpa.domain.Specification;

/**
 * Functional interface for converting a domain-specific Filter DTO
 * into a Spring Data JPA Specification.
 *
 * @param <T>         The Domain Entity type (e.g., Wallpaper, User).
 * @param <FilterDTO> The DTO containing the search criteria.
 */
@FunctionalInterface
public interface FilterSpecificationBuilder<T, FilterDTO> {

    /**
     * Constructs a {@link Specification} based on the provided filter.
     * * @param filter The object containing search parameters.
     *
     * @return A JPA Specification used for dynamic database querying.
     */
    Specification<T> build(FilterDTO filter);
}