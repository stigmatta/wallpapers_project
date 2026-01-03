package com.odintsov.wallpapers_project.domain.pricing;

import com.odintsov.wallpapers_project.domain.entities.BaseProduct;
import com.odintsov.wallpapers_project.domain.entities.OrderItem;

public interface PriceCalculator {
    boolean supports(BaseProduct product);

    Double calculate(BaseProduct product, OrderItem item);

    default double calculateFeaturesPrice(OrderItem item) {
        if (item.getOrderItemExtraFeatures() == null) {
            return 0.0;
        }

        return item.getOrderItemExtraFeatures().stream()
                .filter(link -> link.getExtraFeature() != null)
                .mapToDouble(link -> link.getExtraFeature().getPrice())
                .sum();
    }
}
