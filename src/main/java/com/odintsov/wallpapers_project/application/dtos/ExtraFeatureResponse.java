package com.odintsov.wallpapers_project.application.dtos;

import com.odintsov.wallpapers_project.domain.entities.ProductType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExtraFeatureResponse {
    String id;
    String name;
    String description;
    Double price;
    private ProductType productType;
}
