package com.odintsov.wallpapers_project.infrastructure.adapters.firebase.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDocument {
    private String id;
    private String productType;
    private String productId;
    private Integer quantity;
    private Double price;
    private List<OrderItemExtraFeatureDocument> extraFeatures;
    private Map<String, String> specifications;
}
