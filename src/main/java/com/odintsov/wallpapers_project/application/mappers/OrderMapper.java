package com.odintsov.wallpapers_project.application.mappers;

import com.odintsov.wallpapers_project.application.usecases.GetOrderHistory.OrderHistoryResponse;
import com.odintsov.wallpapers_project.application.usecases.GetOrderHistory.OrderItemHistoryResponse;
import com.odintsov.wallpapers_project.domain.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {
    public OrderHistoryResponse toResponse(Order order) {
        List<OrderItemHistoryResponse> itemResponses = order.getItems().stream()
                .map(item -> new OrderItemHistoryResponse(
                        item.getProductId(),
                        item.getProductType(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .collect(Collectors.toList());

        return new OrderHistoryResponse(
                order.getId(),
                order.getPrice(),
                order.getCreatedAt(),
                itemResponses,
                itemResponses.size()
        );
    }
}
