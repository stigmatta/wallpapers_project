package com.odintsov.wallpapers_project.infrastructure.adapters;

import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperFilter;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.JpaWallpaperRepository;
import com.odintsov.wallpapers_project.infrastructure.utils.BaseProductSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class WallpaperRepositoryAdapter
        extends BaseJpaRepositoryAdapter<Wallpaper, String, WallpaperFilter, JpaWallpaperRepository>
        implements WallpaperRepository {

    public WallpaperRepositoryAdapter(JpaWallpaperRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public Page<Wallpaper> filter(WallpaperFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, BaseProductSpecifications::buildSpecification);
    }
}