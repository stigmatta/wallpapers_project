package com.odintsov.wallpapers_project.application.dtos.common.BaseProduct;

import com.odintsov.wallpapers_project.application.dtos.CategoryResponse;
import com.odintsov.wallpapers_project.domain.entities.ExtraFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

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
    private Set<ExtraFeature> features;
    private List<CategoryResponse> categories;
}
