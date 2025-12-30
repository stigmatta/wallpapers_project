package com.odintsov.wallpapers_project.application.resolvers;

import com.odintsov.wallpapers_project.domain.entities.BaseProduct;
import com.odintsov.wallpapers_project.domain.pricing.PriceCalculator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceCalculatorResolver {

    private final List<PriceCalculator> calculators;

    public PriceCalculatorResolver(List<PriceCalculator> calculators) {
        this.calculators = calculators;
    }

    public PriceCalculator resolve(BaseProduct product) {
        return calculators.stream()
                .filter(c -> c.supports(product))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No price calculator found for product type: " + product.getClass().getSimpleName()));
    }
}

