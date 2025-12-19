package com.odintsov.wallpapers_project.domain.repositories;


import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperFilter;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;

public interface WallpaperRepository extends CrudRepository<Wallpaper, String, WallpaperFilter> {
}
