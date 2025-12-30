package com.odintsov.wallpapers_project.application.dtos.common.BaseProduct;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder
public abstract class BaseProductListResponse {
    private String id;
    private String name;
    private String image;
    private String article;
    private List<String> categoryNames;
    private Double basePrice;
    private Double salePrice;
    private String slug;
}
