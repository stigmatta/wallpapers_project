package com.odintsov.wallpapers_project.application.dtos.common.BaseProduct;

import com.odintsov.wallpapers_project.application.dtos.CategoryResponse;
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
    private List<CategoryResponse> categories;
    private Double basePrice;
    private Double salePrice;
    private String slug;
}
