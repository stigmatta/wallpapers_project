package com.odintsov.wallpapers_project.presentation.controllers;

import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperFilter;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperListResponse;
import com.odintsov.wallpapers_project.application.services.WallpaperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/wallpapers")
public class WallpaperController {

    private final WallpaperService wallpaperService;

    public WallpaperController(WallpaperService wallpaperService) {
        this.wallpaperService = wallpaperService;
    }

    @GetMapping
    public Page<WallpaperListResponse> getWallpapers(
            @ModelAttribute WallpaperFilter filter,
            @PageableDefault(size = 20, sort = "id",
                    direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return wallpaperService.findAll(filter, pageable);
    }

    @GetMapping("/{id}")
    public WallpaperDetailedResponse getById(@PathVariable Long id) {
        return wallpaperService.findById(id);
    }
}
