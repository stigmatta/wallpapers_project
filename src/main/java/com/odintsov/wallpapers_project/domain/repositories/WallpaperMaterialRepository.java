package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.domain.entities.WallpaperMaterial;

import java.util.List;

public interface WallpaperMaterialRepository {
    long count();

    List<WallpaperMaterial> saveAll(List<WallpaperMaterial> materials);

    List<WallpaperMaterial> findAll();
}
