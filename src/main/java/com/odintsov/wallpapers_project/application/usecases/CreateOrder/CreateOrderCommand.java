package com.odintsov.wallpapers_project.application.usecases.CreateOrder;

import com.odintsov.wallpapers_project.application.dtos.OrderItem.OrderItemRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateOrderCommand(
        @NotEmpty @Valid List<OrderItemRequest> items
) {
}
