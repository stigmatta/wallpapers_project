package com.odintsov.wallpapers_project.domain.repositories;

import java.util.Optional;

public interface SlugRepository<T> {
    Optional<T> findBySlug(String slug);
}

