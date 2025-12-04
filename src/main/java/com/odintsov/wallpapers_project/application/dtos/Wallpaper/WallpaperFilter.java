package com.odintsov.wallpapers_project.application.dtos.Wallpaper;

public record WallpaperFilter(
        String name,
        Long category,
        Double basePrice
) {}
