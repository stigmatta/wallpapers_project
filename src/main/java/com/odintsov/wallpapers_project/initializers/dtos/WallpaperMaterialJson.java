package com.odintsov.wallpapers_project.initializers.dtos;

public record WallpaperMaterialJson(
        String name,
        String description,
        Double priceMultiplier,
        String image
) {
}
