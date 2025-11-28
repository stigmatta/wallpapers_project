package com.odintsov.wallpapers_project.application.dtos.Wallpaper;

import java.util.Set;

public record WallpaperFilter(
        String name,
        Long category,
        Double basePrice,
        Set<Long> availableMaterials
) {}
