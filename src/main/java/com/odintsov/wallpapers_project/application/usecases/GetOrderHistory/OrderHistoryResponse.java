package com.odintsov.wallpapers_project.application.usecases.GetOrderHistory;

import java.time.LocalDateTime;
import java.util.List;

public record OrderHistoryResponse(
        String id,
        Double totalPrice,
        LocalDateTime createdAt,
        List<OrderItemHistoryResponse> items,
        int totalItemsCount
) {
}
