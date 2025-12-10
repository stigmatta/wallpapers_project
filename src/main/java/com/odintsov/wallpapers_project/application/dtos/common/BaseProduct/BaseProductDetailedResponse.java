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
    private Long id;
    private String name;
    private String article;
    private Float basePrice;
    private Float salePrice;
    private String image;
    private String description;
}
