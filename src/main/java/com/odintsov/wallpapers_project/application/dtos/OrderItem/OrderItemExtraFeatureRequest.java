package com.odintsov.wallpapers_project.application.dtos.OrderItem;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for specifying a customization option during order creation.
 */
public record OrderItemExtraFeatureRequest(
        @NotBlank(message = "Feature ID is required")
        String featureId,
        String value
) {
}