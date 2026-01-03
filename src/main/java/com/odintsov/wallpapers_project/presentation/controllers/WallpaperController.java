package com.odintsov.wallpapers_project.presentation.controllers;

import com.odintsov.wallpapers_project.application.dtos.CategoryResponse;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperFilter;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperListResponse;
import com.odintsov.wallpapers_project.application.services.WallpaperService;
import com.odintsov.wallpapers_project.presentation.dtos.WallpaperCatalogResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST Controller for accessing the wallpaper catalog.
 * <p>
 * Provides endpoints for paginated browsing with complex filtering capabilities,
 * as well as detailed resource retrieval via unique identifiers or SEO-friendly slugs.
 * </p>
 */
@RestController
@RequestMapping("/wallpapers")
public class WallpaperController {

    private final WallpaperService wallpaperService;

    public WallpaperController(WallpaperService wallpaperService) {
        this.wallpaperService = wallpaperService;
    }

    @GetMapping
    public ResponseEntity<WallpaperCatalogResponse> getWallpapers(
            @ModelAttribute WallpaperFilter filter,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<WallpaperListResponse> wallpapersPage = wallpaperService.findAll(filter, pageable);

        List<CategoryResponse> categories = wallpaperService.getAvailableCategories();

        return ResponseEntity.ok(
                WallpaperCatalogResponse.builder()
                        .products(wallpapersPage)
                        .availableCategories(categories)
                        .build()
        );
    }

    @GetMapping("/{id:[0-9a-fA-F-]{36}}")
    public WallpaperDetailedResponse getById(@PathVariable String id) {
        return wallpaperService.findById(id);
    }

    @GetMapping("/{slug}")
    public WallpaperDetailedResponse getBySlug(@PathVariable String slug) {
        return wallpaperService.findBySlug(slug);
    }
}