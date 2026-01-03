package com.odintsov.wallpapers_project.initializers.dtos;


import java.util.List;

public record WallpaperJson(
        String name,
        String slug,
        String article,
        Double basePrice,
        Double salePrice,
        String image,
        String description,
        Float density,
        Boolean waterproof,
        Integer quantity,
        List<String> rooms,
        List<String> materials,
        List<String> categories) {
}
