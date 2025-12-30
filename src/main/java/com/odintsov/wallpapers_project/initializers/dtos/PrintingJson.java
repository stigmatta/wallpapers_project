package com.odintsov.wallpapers_project.initializers.dtos;

import java.util.List;

public record PrintingJson(
        String name,
        String article,
        Double basePrice,
        Double salePrice,
        String image,
        String description,
        Integer quantity,
        List<String> methods
) {
}
