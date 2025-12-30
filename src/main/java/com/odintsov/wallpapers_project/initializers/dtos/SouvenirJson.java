package com.odintsov.wallpapers_project.initializers.dtos;

import java.util.List;

public record SouvenirJson(
        String name,
        String image,
        String article,
        Double basePrice,
        int quantity,
        String description,
        Double width,
        Double length,
        Double thickness,
        List<String> categories
) {
}

