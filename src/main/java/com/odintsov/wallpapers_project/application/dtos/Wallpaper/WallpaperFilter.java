package com.odintsov.wallpapers_project.application.dtos.Wallpaper;

import com.odintsov.wallpapers_project.domain.entities.ProductCategory;
import com.odintsov.wallpapers_project.domain.entities.WallpaperMaterial;

import java.util.Set;

public record WallpaperFilter(
        String name,
        ProductCategory category,
        Double basePrice,
        Set<WallpaperMaterial> availableMaterials
) {}
