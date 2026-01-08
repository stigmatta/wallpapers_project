package com.odintsov.wallpapers_project.domain.enums;

public final class ProductFields {
    public static final String PRICE = "price";
    public static final String SALE_PRICE = "salePrice";
    public static final String EFFECTIVE_PRICE = "effectivePrice";
    public static final String RATING = "rating";
    public static final String QUANTITY = "quantity";
    public static final String IMAGE = "image";
    public static final String ARTICLE = "article";
    public static final String IMAGE_URL = "imageUrl";
    public static final String DEADLINE = "deadline";
    public static final String ATTRIBUTE_KEY = "attribute_key";
    public static final String SPEC_KEY = "spec_key";
    public static final String SPEC_VALUE = "spec_value";

    private ProductFields() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
