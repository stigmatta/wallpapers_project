package com.odintsov.wallpapers_project.infrastructure.persistence.jpa;

import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface JpaWallpaperRepository
        extends JpaRepository<Wallpaper, String>, JpaSpecificationExecutor<Wallpaper> {
    Optional<Wallpaper> findBySlug(String slug);
}
