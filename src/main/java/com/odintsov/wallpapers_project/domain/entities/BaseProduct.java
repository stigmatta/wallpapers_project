package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.ProductFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.Set;

/**
 * Abstract base class for all product-based entities in the system.
 * <p>
 * This mapped superclass provides common ecommerce attributes such as pricing,
 * inventory levels, and media galleries. It also includes automatic slug
 * generation logic to support SEO-friendly URLs.
 * </p>
 * * @see Wallpaper
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class BaseProduct {

    /** * Unique identifier for the product, generated as a UUID String.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    protected String id;

    /** Display name of the product. */
    @Column(name = CommonFields.NAME, nullable = false)
    protected String name;

    /** URL-friendly identifier derived from the name. */
    @Column(name = CommonFields.SLUG, nullable = false, unique = true)
    protected String slug;

    /** Detailed marketing description of the product. */
    @Column(name = CommonFields.DESCRIPTION, nullable = false)
    protected String description;

    /** Unique Stock Keeping Unit (SKU) or internal article reference. */
    @Column(name = ProductFields.ARTICLE, nullable = false, unique = true)
    protected String article;

    /** Original retail price. */
    @Column(name = ProductFields.PRICE, nullable = false)
    protected Float price;

    /** Current selling price after discounts. */
    @Column(name = ProductFields.SALE_PRICE, nullable = false)
    protected Float salePrice;

    /** Average customer rating score. */
    @Column(name = ProductFields.RATING, nullable = false)
    protected Float rating;

    /** Current stock level in the inventory. */
    @Column(name = ProductFields.QUANTITY, nullable = false)
    protected Integer quantity;

    /** Set of additional characteristics or features associated with the product. */
    @ManyToMany
    @JoinTable(
            name = ProductFields.PRODUCT_EXTRA_FEATURE_LINK,
            joinColumns = @JoinColumn(name = IdFields.PRODUCT_ID),
            inverseJoinColumns = @JoinColumn(name = IdFields.EXTRA_FEATURE_ID)
    )
    protected Set<ExtraFeature> extraFeatures;

    /** Primary display image URL. */
    @Column(name = ProductFields.IMAGE, nullable = false)
    protected String image;

    /** List of secondary image URLs for the product gallery. */
    @ElementCollection
    @CollectionTable(
            name = TableNames.PRODUCT_GALLERIES,
            joinColumns = @JoinColumn(name = IdFields.PRODUCT_ID)
    )
    @Column(name = ProductFields.IMAGE_URL)
    protected List<String> gallery;

    /**
     * Lifecycle callback to automatically generate a slug from the name
     * if one is not explicitly provided.
     * <p>
     * Converts to lowercase, removes special characters, and replaces spaces with hyphens.
     * </p>
     */
    @PrePersist
    @PreUpdate
    public void generateSlug() {
        if (this.slug == null && this.name != null) {
            this.slug = this.name.toLowerCase()
                    .replaceAll("[^a-z0-9\\s-]", "")
                    .replaceAll("\\s+", "-");
        }
    }
}