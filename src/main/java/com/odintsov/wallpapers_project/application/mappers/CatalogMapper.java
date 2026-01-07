package com.odintsov.wallpapers_project.application.mappers;

import com.odintsov.wallpapers_project.application.dtos.CategoryResponse;
import com.odintsov.wallpapers_project.application.dtos.ExtraFeatureResponse;
import com.odintsov.wallpapers_project.application.dtos.ProductTypeResponse;
import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.entities.ExtraFeature;
import com.odintsov.wallpapers_project.domain.entities.ProductType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatalogMapper {

    public CategoryResponse categoryToResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public List<CategoryResponse> categoryToResponseList(List<Category> categories) {
        return categories.stream()
                .map(this::categoryToResponse)
                .collect(Collectors.toList());
    }

    public ExtraFeatureResponse featureToResponse(ExtraFeature feature) {
        return ExtraFeatureResponse.builder()
                .id(feature.getId())
                .name(feature.getName())
                .description(feature.getDescription())
                .price(feature.getPrice())
                .productType(feature.getProductType())
                .build();
    }

    public ProductTypeResponse typeToResponse (ProductType type) {
        return ProductTypeResponse.builder()
                .id(type.getId())
                .name(type.getName())
                .build();
    }
}