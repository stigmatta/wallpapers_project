package com.odintsov.wallpapers_project.application.dtos.common.BaseProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseProductFilter {
    private String name;
    private String categoryId;
    private Double basePrice;
}
