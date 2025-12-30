package com.odintsov.wallpapers_project.infrastructure.adapters.firebase.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemExtraFeatureDocument {
    private String extraFeatureId;
    private String value;
}
