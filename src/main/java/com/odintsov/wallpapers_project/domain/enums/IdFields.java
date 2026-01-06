package com.odintsov.wallpapers_project.domain.enums;

/**
 * Utility class containing database column name constants for Foreign Keys.
 * <p>
 * This class centralizes the naming of ID references used in {@code @JoinColumn}
 * annotations. By standardizing these names, the system ensures that
 * relationship mappings across different entities and join tables
 * (e.g., Many-to-Many links) remain consistent and maintainable.
 */
public final class IdFields {

    public static final String USER_ID = "userId";
    public static final String ORDER_ID = "orderId";
    public static final String ORDER_ITEM_ID = "orderItemId";
    public static final String ORDER_HISTORY_ID = "orderHistoryId";
    public static final String WALLPAPER_ID = "wallpaperId";
    public static final String WALLPAPER_MATERIAL_ID = "wallpaperMaterialId";
    public static final String SOUVENIR_ID = "souvenirId";
    public static final String PRINTING_ID = "printingId";
    public static final String PRODUCT_ID = "productId";
    public static final String PRODUCT_TYPE_ID = "productTypeId";
    public static final String PRINT_METHOD_ID = "printMethodId";
    public static final String EXTRA_FEATURE_ID = "extraFeatureId";
    public static final String CATEGORY_ID = "categoryId";
    public static final String CATEGORY_IDS = "categoryIds";
    public static final String METHOD_IDS = "methodIds";
    public static final String ROOM_ID = "roomId";

    /**
     * Private constructor to prevent instantiation.
     */
    private IdFields() {
        throw new UnsupportedOperationException("Utility class");
    }
}