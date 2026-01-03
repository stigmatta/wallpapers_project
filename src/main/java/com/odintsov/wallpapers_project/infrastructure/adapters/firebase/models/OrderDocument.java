package com.odintsov.wallpapers_project.infrastructure.adapters.firebase.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDocument {
    private String id;
    private String userId;
    private Double price;
    private String createdAt;
    private List<OrderItemDocument> items;
}
