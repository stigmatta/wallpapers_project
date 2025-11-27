package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import org.springframework.stereotype.Repository;

@Repository
public interface WallpaperRepository extends BaseRepository<Wallpaper, Long> {}
