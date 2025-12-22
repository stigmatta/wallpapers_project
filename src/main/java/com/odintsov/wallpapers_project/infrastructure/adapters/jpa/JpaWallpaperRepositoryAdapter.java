package com.odintsov.wallpapers_project.infrastructure.adapters.jpa;

import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperFilter;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.jpa.JpaWallpaperRepository;
import com.odintsov.wallpapers_project.infrastructure.utils.BaseProductSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.Optional; // <--- Import this!

@Component
public class JpaWallpaperRepositoryAdapter
        extends BaseJpaRepositoryAdapter<Wallpaper, String, WallpaperFilter, JpaWallpaperRepository>
        implements WallpaperRepository {

    public JpaWallpaperRepositoryAdapter(JpaWallpaperRepository jpaRepository) {
        super(jpaRepository);
    }

    // --- ADD THIS IMPLEMENTATION ---
    @Override
    public Optional<Wallpaper> findBySlug(String slug) {
        // Delegate the specific query to the Spring Data JPA repository
        return jpaRepository.findBySlug(slug);
    }
    // -------------------------------

    @Override
    public Page<Wallpaper> filter(WallpaperFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, BaseProductSpecifications::buildSpecification);
    }
}