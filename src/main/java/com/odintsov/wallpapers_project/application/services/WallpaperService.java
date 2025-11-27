package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class WallpaperService extends BaseCrudService<Wallpaper,Long>
{
    protected WallpaperService(JpaRepository<Wallpaper, Long> repository) {
        super(repository);
    }
}
