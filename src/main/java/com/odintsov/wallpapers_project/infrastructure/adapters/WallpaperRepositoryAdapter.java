package com.odintsov.wallpapers_project.infrastructure.adapters;

import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.JpaWallpaperRepository;
import org.springframework.stereotype.Component;

@Component
public class WallpaperRepositoryAdapter
        extends BaseJpaRepositoryAdapter<Wallpaper, Long, JpaWallpaperRepository>
        implements WallpaperRepository {

    public WallpaperRepositoryAdapter(JpaWallpaperRepository jpaRepository) {
        super(jpaRepository);
    }
}