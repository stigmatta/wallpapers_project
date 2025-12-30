package com.odintsov.wallpapers_project.initializers.dtos;

import java.util.List;

public record SouvenirJson(
        String name,
        String image,
        String article,
        float basePrice,
        int quantity,
        String description,
        float width,
        float length,
        float thickness,
        List<String> categories
) {
}

