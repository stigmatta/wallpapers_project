package com.odintsov.wallpapers_project.domain.enums;

public final class WallpaperFields {
    public static final String DENSITY = "density";
    public static final String WATERPROOF = "waterproof";
    public static final String PRICE_MULTIPLIER = "priceMultiplier";

    private WallpaperFields() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
