package com.odintsov.wallpapers_project.domain.pricing;

import com.odintsov.wallpapers_project.domain.entities.BaseProduct;
import com.odintsov.wallpapers_project.domain.entities.OrderItem;
import com.odintsov.wallpapers_project.domain.enums.ProductTypes;
import com.odintsov.wallpapers_project.domain.enums.WallpaperFields;
import org.springframework.stereotype.Component;

@Component
public class WallpaperPriceCalculator implements PriceCalculator {

    @Override
    public boolean supports(BaseProduct product) {
        return product.getProductType().getName().equalsIgnoreCase(ProductTypes.WALLPAPER);
    }

    @Override
    public Double calculate(BaseProduct product, OrderItem item) {
        double width = Double.parseDouble(String.valueOf(item.getSpecifications().get(WallpaperFields.WIDTH)));
        double height = Double.parseDouble(String.valueOf(item.getSpecifications().get(WallpaperFields.HEIGHT)));

        double area = (width / 100.0) * (height / 100.0);

        double baseUnitPrice = product.getPrice() * area;

        double featuresPricePerUnit = calculateFeaturesPrice(item);

        return (baseUnitPrice + featuresPricePerUnit) * item.getQuantity();
    }

}

