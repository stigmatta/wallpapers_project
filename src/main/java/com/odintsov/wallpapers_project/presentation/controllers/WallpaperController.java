package com.odintsov.wallpapers_project.presentation.controllers;

import com.odintsov.wallpapers_project.application.services.WallpaperService;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallpapers")
public class WallpaperController {

    private final WallpaperService wallpaperService;

    public WallpaperController(WallpaperService wallpaperService) {
        this.wallpaperService = wallpaperService;
    }

    @GetMapping
    public List<Wallpaper> getAll()
    {
        return wallpaperService.findAll();
    }

    @GetMapping("/{id}")
    public Wallpaper getById(@PathVariable Long id)
    {
        return wallpaperService.findById(id);
    }
}
