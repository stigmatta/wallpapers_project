package com.odintsov.wallpapers_project.application.usecases.GetOrderHistory;

public record OrderItemHistoryResponse(
        String name,
        String productId,
        String productType,
        Integer quantity,
        Double purchasePrice
) {
}
