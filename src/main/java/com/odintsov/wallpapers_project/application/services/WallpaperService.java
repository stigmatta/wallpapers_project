package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperFilter;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperListResponse;
import com.odintsov.wallpapers_project.application.mappers.WallpaperMapper;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class WallpaperService extends BaseCrudService<
        Wallpaper,
        String,
        WallpaperFilter,
        WallpaperListResponse,
        WallpaperDetailedResponse,
        WallpaperRepository
        > {

    public WallpaperService(WallpaperRepository repository,
                            WallpaperMapper mapper) {
        super(repository, mapper);
    }

    // --- ADD THIS METHOD ---
    public WallpaperDetailedResponse findBySlug(String slug) {
        Wallpaper wallpaper = repository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Wallpaper not found with slug: " + slug));

        // Ensure your BaseMapper or WallpaperMapper has this method exposed
        return mapper.toDetailedResponseDto(wallpaper);
    }
}