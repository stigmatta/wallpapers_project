package com.odintsov.wallpapers_project.domain.pricing;

import com.odintsov.wallpapers_project.domain.entities.BaseProduct;
import com.odintsov.wallpapers_project.domain.entities.OrderItem;
import com.odintsov.wallpapers_project.domain.enums.ProductTypes;
import com.odintsov.wallpapers_project.domain.enums.SouvenirFields;
import org.springframework.stereotype.Component;

@Component
public class SouvenirPriceCalculator implements PriceCalculator {

    @Override
    public boolean supports(BaseProduct product) {
        return product.getProductType().getName().equalsIgnoreCase(ProductTypes.SOUVENIR);
    }

    @Override
    public Double calculate(BaseProduct product, OrderItem item) {
        double baseUnitPrice = product.getPrice();

        Object giftWrap = item.getSpecifications().get(SouvenirFields.GIFT_WRAP);
        if (giftWrap != null && giftWrap.toString().equalsIgnoreCase("true")) {
            baseUnitPrice += 50.0;
        }

        return (baseUnitPrice + calculateFeaturesPrice(item)) * item.getQuantity();
    }
}
