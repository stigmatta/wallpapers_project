package com.odintsov.wallpapers_project.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemExtraFeatureId implements Serializable {
    private Long orderItem;
    private Long extraFeature;
}
