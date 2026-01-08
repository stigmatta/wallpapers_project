package com.odintsov.wallpapers_project.application.usecases.GlobalSearch;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchItemResponse {
    private String id;
    private String name;
    private String article;
    private String productType;
    private Double basePrice;
    private Double salePrice;
    private String slug;
    private String image;
}
