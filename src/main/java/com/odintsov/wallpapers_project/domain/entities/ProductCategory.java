package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

/**
 * Entity representing a specific sub-category for products.
 * <p>
 * This class provides a secondary level of classification, allowing products
 * to be grouped under a specific {@link ProductType}. It is used to build
 * navigation menus and filter products in the storefront.
 */
@Entity
@Table(name = TableNames.PRODUCT_CATEGORIES)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductCategory {

    /**
     * Unique identifier for the product category.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    @EqualsAndHashCode.Include
    protected String id;

    /**
     * The unique display name of the category (e.g., "Minimalist", "Industrial").
     */
    @Column(name = CommonFields.NAME, nullable = false, unique = true)
    private String name;

    /**
     * The parent product type that this category belongs to.
     * <p>
     * Establishes a hierarchical relationship (e.g., Wallpapers -> Floral).
     */
    @ManyToOne
    @JoinColumn(name = IdFields.PRODUCT_TYPE_ID)
    private ProductType productType;
}