package com.odintsov.wallpapers_project.application.usecases.CreateOrder;

import java.time.LocalDateTime;

/**
 * Record returned after a successful order creation.
 * Provides the client with the essential data needed for confirmation
 * or redirection to a payment gateway.
 */
public record CreateOrderResponse(
        String orderId,
        Double totalPrice,
        LocalDateTime createdAt,
        int itemsCount,
        String message
) {
}