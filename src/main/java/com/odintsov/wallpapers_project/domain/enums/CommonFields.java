package com.odintsov.wallpapers_project.domain.enums;

/**
 * Utility class containing shared database column name constants.
 * <p>
 * This class serves as a single source of truth for field names used across
 * multiple entities (e.g., ID, Name, CreatedAt). Using these constants in
 * JPA annotations ensures schema consistency and simplifies refactoring.
 * </p>
 */
public final class CommonFields {

    /**
     * Database column name for primary identifiers.
     */
    public static final String ID = "id";

    /**
     * Database column name for display names or titles.
     */
    public static final String NAME = "name";

    /**
     * Database column name for long-form text descriptions.
     */
    public static final String DESCRIPTION = "description";

    /**
     * Database column name for record creation timestamps.
     */
    public static final String CREATED_AT = "createdAt";

    /**
     * Database column name for entity lifecycle states (e.g., Order status).
     */
    public static final String STATUS = "status";

    /**
     * Database column name for URL-friendly resource identifiers.
     */
    public static final String SLUG = "slug";

    /**
     * Database column name for discriminating between product categories.
     */
    public static final String PRODUCT_TYPE = "productType";

    /**
     * Database column name for generic value fields in link tables.
     */
    public static final String VALUE = "value";

    public static final String DATA_TYPE = "data_type";

    public static final String IS_REQUIRED = "is_required";

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private CommonFields() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}