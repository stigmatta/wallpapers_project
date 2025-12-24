package com.odintsov.wallpapers_project.initializers.dtos;

import java.util.List;

public record WallpaperJson(
        String name,
        String article,
        Float basePrice,
        Float salePrice,
        String image,
        String description,
        Float density,
        Boolean waterproof,
        Integer quantity,
        List<String> rooms,
        List<String> materials
) {}
