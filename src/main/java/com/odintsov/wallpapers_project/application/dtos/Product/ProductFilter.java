package com.odintsov.wallpapers_project.application.dtos.Product;

import com.odintsov.wallpapers_project.application.dtos.common.BaseProduct.BaseProductFilter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ProductFilter extends BaseProductFilter {
    String productTypeId;
}
