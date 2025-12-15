package com.odintsov.wallpapers_project.infrastructure.persistence;

import com.odintsov.wallpapers_project.domain.entities.WallpaperMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaWallpaperMaterialRepository
        extends JpaRepository<WallpaperMaterial, Long>, JpaSpecificationExecutor<WallpaperMaterial> {
}
