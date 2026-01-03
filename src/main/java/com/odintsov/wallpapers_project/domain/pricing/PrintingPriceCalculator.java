package com.odintsov.wallpapers_project.domain.pricing;


import com.odintsov.wallpapers_project.domain.entities.BaseProduct;
import com.odintsov.wallpapers_project.domain.entities.OrderItem;
import com.odintsov.wallpapers_project.domain.enums.ProductTypes;
import org.springframework.stereotype.Component;

@Component
public class PrintingPriceCalculator implements PriceCalculator {

    @Override
    public boolean supports(BaseProduct product) {
        return product.getProductType().getName().equalsIgnoreCase(ProductTypes.PRINTING);
    }

    @Override
    public Double calculate(BaseProduct product, OrderItem item) {
        double baseUnitPrice = product.getPrice();

        Object density = item.getSpecifications().get("paper_density");
        if (density != null) {
            baseUnitPrice += switch (density.toString()) {
                case "350g" -> 20.0;
                case "130g" -> 7.5;
                default -> 0.0;
            };
        }

        double featuresPrice = calculateFeaturesPrice(item);

        return (baseUnitPrice + featuresPrice) * item.getQuantity();
    }
}