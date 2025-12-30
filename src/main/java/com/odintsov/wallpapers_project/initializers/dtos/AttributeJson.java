package com.odintsov.wallpapers_project.initializers.dtos;

import java.util.Map;

public record AttributeJson(
        String productType,
        String attributeKey,
        String dataType,
        boolean isRequired,
        Map<String, Object> validationRules
) {
}
