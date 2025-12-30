package com.odintsov.wallpapers_project.application.dtos.OrderItem;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;

public record OrderItemRequest(
        @NotBlank(message = "Product type is required")
        String productTypeId,

        @NotBlank(message = "Product ID is required")
        String productId,

        @Min(value = 1, message = "Quantity must be at least 1")
        Integer quantity,

        @NotNull(message = "Specifications are required")
        Map<String, String> specifications,

        @Valid
        List<OrderItemExtraFeatureRequest> features
) {
}
