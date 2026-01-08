package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.ProductFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

/**
 * Abstract base class for all product-based entities in the system.
 * <p>
 * This mapped superclass provides common ecommerce attributes such as pricing,
 * inventory levels, and media galleries. It also includes automatic slug
 * generation logic to support SEO-friendly URLs.
 * </p>
 * * @see Wallpaper
 */
@Entity
@Table(name = TableNames.PRODUCTS)
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseProduct {

    /**
     * Unique identifier for the product, generated as a UUID String.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    protected String id;

    /**
     * Display name of the product.
     */
    @Column(name = CommonFields.NAME, nullable = false)
    protected String name;

    /**
     * URL-friendly identifier derived from the name.
     */
    @Column(name = CommonFields.SLUG, nullable = false, unique = true)
    protected String slug;

    /**
     * Detailed marketing description of the product.
     */
    @Column(name = CommonFields.DESCRIPTION, nullable = false)
    protected String description;

    /**
     * Unique Stock Keeping Unit (SKU) or internal article reference.
     */
    @Column(name = ProductFields.ARTICLE, nullable = false, unique = true)
    protected String article;

    /**
     * Original retail price.
     */
    @Column(name = ProductFields.PRICE, nullable = false)
    protected Double price;

    /**
     * Current selling price after discounts.
     */
    @Column(name = ProductFields.SALE_PRICE, nullable = false)
    protected Double salePrice;

    @Column(name = ProductFields.EFFECTIVE_PRICE)
    protected Double effectivePrice;

    /**
     * Average customer rating score.
     */
    @Column(name = ProductFields.RATING, nullable = false)
    protected Double rating;

    /**
     * Current stock level in the inventory.
     */
    @Column(name = ProductFields.QUANTITY, nullable = false)
    protected Integer quantity;

    /**
     * Primary display image URL.
     */
    @Column(name = ProductFields.IMAGE, nullable = false)
    protected String image;

    /**
     * List of secondary image URLs for the product gallery.
     */
    @ElementCollection
    @CollectionTable(
            name = TableNames.PRODUCT_GALLERIES,
            joinColumns = @JoinColumn(name = IdFields.PRODUCT_ID)
    )
    @Column(name = ProductFields.IMAGE_URL)

    protected List<String> gallery;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = IdFields.PRODUCT_TYPE_ID, nullable = false)
    protected ProductType productType;

    @ManyToMany
    @JoinTable(
            name = TableNames.PRODUCT_CATEGORY_LINKS,
            joinColumns = @JoinColumn(name = IdFields.PRODUCT_ID),
            inverseJoinColumns = @JoinColumn(name = IdFields.CATEGORY_ID)
    )
    protected List<Category> categories;

    @ElementCollection
    @CollectionTable(name = IdFields.CATEGORY_IDS)
    protected List<String> categoryIds;

    public void syncCategoryIds() {
        if (this.categories != null) {
            this.categoryIds = this.categories.stream()
                    .map(Category::getId)
                    .toList();
        }
    }

    /**
     * Lifecycle callback to automatically generate a slug from the name
     * if one is not explicitly provided.
     * <p>
     * Converts to lowercase, removes special characters, and replaces spaces with hyphens.
     * </p>
     */
    public void calculateEffectivePrice() {
        if (this.salePrice != null && this.salePrice > 0 && this.salePrice < this.price) {
            this.effectivePrice = this.salePrice;
        } else {
            this.effectivePrice = this.price;
        }
    }
}