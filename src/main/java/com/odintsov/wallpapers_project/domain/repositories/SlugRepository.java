package com.odintsov.wallpapers_project.domain.repositories;

import java.util.Optional;

/**
 * A specialized repository trait for entities that can be retrieved via a unique slug.
 * <p>
 * Slugs are human-readable, URL-friendly strings used as alternative identifiers
 * to primary keys. This is commonly used in E-commerce for Product, Category,
 * or Blog post lookups to improve SEO and user experience.
 * </p>
 * @param <T> The entity type that possesses a slug attribute.
 */
public interface SlugRepository<T> {

    /**
     * Finds an entity based on its unique URL slug.
     * * @param slug The URL-safe string identifier (e.g., "minimalist-vinyl-wallpaper").
     * @return An Optional containing the entity if found, otherwise empty.
     */
    Optional<T> findBySlug(String slug);
}