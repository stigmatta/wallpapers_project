package com.odintsov.wallpapers_project.application.dtos.Wallpaper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Lightweight DTO for wallpaper list responses.
 * Contains only essential fields for displaying in lists.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WallpaperListResponse {

    private Long id;
    private String name;
    private String image;
    private List<String> categoryNames;
    private Float basePrice;
    private Float salePrice;
}