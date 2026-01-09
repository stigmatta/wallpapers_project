package com.odintsov.wallpapers_project.application.mappers;

import com.odintsov.wallpapers_project.application.usecases.GetOrderHistory.OrderHistoryResponse;
import com.odintsov.wallpapers_project.application.usecases.GetOrderHistory.OrderItemHistoryResponse;
import com.odintsov.wallpapers_project.domain.entities.BaseProduct;
import com.odintsov.wallpapers_project.domain.entities.Order;
import com.odintsov.wallpapers_project.domain.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    private final ProductRepository productRepository;

    public OrderMapper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public OrderHistoryResponse toResponse(Order order) {
        List<OrderItemHistoryResponse> itemResponses = order.getItems().stream()
                .map(item -> {
                    String productName = productRepository.findById(item.getProductId())
                            .map(BaseProduct::getName)
                            .orElse("Товар видалено");

                    return new OrderItemHistoryResponse(
                            productName,
                            item.getProductId(),
                            item.getProductType(),
                            item.getQuantity(),
                            item.getPrice()
                    );
                })
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