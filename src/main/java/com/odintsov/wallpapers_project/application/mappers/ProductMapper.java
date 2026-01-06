package com.odintsov.wallpapers_project.application.mappers;

import com.odintsov.wallpapers_project.application.usecases.GlobalSearch.SearchItemResponse;
import com.odintsov.wallpapers_project.domain.entities.BaseProduct;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public SearchItemResponse toResponse(BaseProduct product) {
        return SearchItemResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .productType(product.getProductType().getName())
                .build();
    }
}
