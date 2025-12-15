package com.odintsov.wallpapers_project.infrastructure.persistence;

import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaWallpaperRepository
        extends JpaRepository<Wallpaper, Long>, JpaSpecificationExecutor<Wallpaper> {
}
