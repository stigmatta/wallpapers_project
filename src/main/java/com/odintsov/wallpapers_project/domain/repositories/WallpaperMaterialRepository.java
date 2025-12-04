package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.domain.entities.WallpaperMaterial;
import org.springframework.stereotype.Repository;

@Repository
public interface WallpaperMaterialRepository extends BaseRepository<WallpaperMaterial, Long> {}
