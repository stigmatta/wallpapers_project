package com.odintsov.wallpapers_project.application.dtos.common.BaseProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseProductDetailedResponse {
    private String id;
    private String name;
    private String article;
    private Double basePrice;
    private Double salePrice;
    private String image;
    private String description;
}
