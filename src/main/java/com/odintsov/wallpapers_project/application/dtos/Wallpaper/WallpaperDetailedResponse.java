package com.odintsov.wallpapers_project.application.dtos.Wallpaper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WallpaperDetailedResponse {
    private Long id;
    private String name;
    private String article;
    private Float basePrice;
    private Float salePrice;
    private String image;
    private String description;

    private Float density;
    private Boolean waterproof;
    private Set<String> rooms;
}
