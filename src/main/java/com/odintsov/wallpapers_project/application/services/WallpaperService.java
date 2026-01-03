package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperFilter;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperListResponse;
import com.odintsov.wallpapers_project.application.mappers.WallpaperMapper;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import com.odintsov.wallpapers_project.domain.enums.ProductTypes;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRepository;
import org.springframework.stereotype.Service;

@Service
public class WallpaperService extends BaseProductService<
        Wallpaper, String, WallpaperFilter, WallpaperListResponse, WallpaperDetailedResponse, WallpaperRepository
        > {

    public WallpaperService(WallpaperRepository repository, WallpaperMapper mapper) {
        super(repository, mapper, ProductTypes.WALLPAPER);
    }
}