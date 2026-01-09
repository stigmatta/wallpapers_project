package com.odintsov.wallpapers_project.infrastructure.mappers;

import com.odintsov.wallpapers_project.domain.entities.*;
import com.odintsov.wallpapers_project.infrastructure.adapters.firebase.models.OrderDocument;
import com.odintsov.wallpapers_project.infrastructure.adapters.firebase.models.OrderItemDocument;
import com.odintsov.wallpapers_project.infrastructure.adapters.firebase.models.OrderItemExtraFeatureDocument;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FirebaseOrderMapper {

    public OrderDocument toDocument(Order order) {
        if (order == null) return null;

        return OrderDocument.builder()
                .id(order.getId())
                .userId(order.getUser() != null ? order.getUser().getId() : null)
                .price(order.getPrice())
                .createdAt(order.getCreatedAt() != null ? order.getCreatedAt().toString() : null)
                .items(mapItemsToDocument(order.getItems()))
                .build();
    }

    private List<OrderItemDocument> mapItemsToDocument(List<OrderItem> items) {
        if (items == null) return new ArrayList<>();
        return items.stream().map(item -> OrderItemDocument.builder()
                .id(item.getId())
                .productType(item.getProductType())
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .specifications(item.getSpecifications())
                .extraFeatures(item.getOrderItemExtraFeatures().stream()
                        .map(f -> OrderItemExtraFeatureDocument.builder()
                                .extraFeatureId(f.getExtraFeature().getId())
                                .value(f.getValue())
                                .build())
                        .toList())
                .build()).toList();
    }


    public Order toDomain(OrderDocument doc) {
        if (doc == null) return null;

        Order order = Order.builder()
                .id(doc.getId())
                .price(doc.getPrice())
                .createdAt(doc.getCreatedAt() != null ? LocalDateTime.parse(doc.getCreatedAt()) : null)
                .user(doc.getUserId() != null ? User.builder().id(doc.getUserId()).build() : null)
                .build();

        if (doc.getItems() != null) {
            order.setItems(doc.getItems().stream()
                    .map(itemDoc -> toOrderItemDomain(itemDoc, order))
                    .collect(Collectors.toList()));
        }
        return order;
    }

    private OrderItem toOrderItemDomain(OrderItemDocument itemDoc, Order parentOrder) {
        OrderItem item = OrderItem.builder()
                .id(itemDoc.getId())
                .order(parentOrder)
                .productType(itemDoc.getProductType())
                .productId(itemDoc.getProductId())
                .quantity(itemDoc.getQuantity())
                .price(itemDoc.getPrice())
                .specifications(itemDoc.getSpecifications())
                .build();

        if (itemDoc.getExtraFeatures() != null) {
            item.setOrderItemExtraFeatures(itemDoc.getExtraFeatures().stream()
                    .map(fDoc -> OrderItemExtraFeature.builder()
                            .orderItem(item)
                            .extraFeature(ExtraFeature.builder().id(fDoc.getExtraFeatureId()).build())
                            .value(fDoc.getValue())
                            .build())
                    .collect(Collectors.toList()));
        }
        return item;
    }
}