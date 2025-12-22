package com.odintsov.wallpapers_project.domain.repositories;
import java.util.Optional;

import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperFilter;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;

public interface WallpaperRepository extends CrudRepository<Wallpaper, String, WallpaperFilter> {
    Optional<Wallpaper> findBySlug(String slug);
}
