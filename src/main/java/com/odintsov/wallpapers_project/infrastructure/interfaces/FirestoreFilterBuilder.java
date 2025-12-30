package com.odintsov.wallpapers_project.infrastructure.interfaces;

import com.google.cloud.firestore.Query;

/**
 * Functional interface for applying domain-specific filters to a Firestore Query.
 * <p>
 * Implementations of this interface translate Domain Filter DTOs into
 * Firestore-specific query constraints like {@code whereEqualTo},
 * {@code whereArrayContains}, or range filters.
 * </p>
 *
 * @param <F> The Type of the Filter DTO.
 */
@FunctionalInterface
public interface FirestoreFilterBuilder<F> {

    /**
     * Augments a base Firestore query with additional constraints based on the filter DTO.
     * * @param baseQuery The initial collection reference or query.
     *
     * @param filter The object containing search criteria.
     * @return A new Query object containing the applied filters.
     */
    Query apply(Query baseQuery, F filter);
}