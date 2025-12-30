package com.odintsov.wallpapers_project.domain.pricing;

import com.odintsov.wallpapers_project.domain.entities.BaseProduct;
import com.odintsov.wallpapers_project.domain.entities.OrderItem;

public interface PriceCalculator {
    boolean supports(BaseProduct product);

    Double calculate(BaseProduct product, OrderItem item);
}
