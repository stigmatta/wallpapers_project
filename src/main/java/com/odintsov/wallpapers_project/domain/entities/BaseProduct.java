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
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class BaseProduct {

    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    protected String id;

    @Column(name = CommonFields.NAME, nullable = false)
    protected String name;

    @Column(name = CommonFields.SLUG, nullable = false, unique = true)
    protected String slug;

    @Column(name = CommonFields.DESCRIPTION, nullable = false)
    protected String description;

    @Column(name = ProductFields.ARTICLE, nullable = false, unique = true)
    protected String article;

    @Column(name = ProductFields.PRICE, nullable = false)
    protected Float price;

    @Column(name = ProductFields.SALE_PRICE, nullable = false)
    protected Float salePrice;

    @Column(name = ProductFields.RATING, nullable = false)
    protected Float rating;

    @Column(name = ProductFields.QUANTITY, nullable = false)
    protected Integer quantity;

    @ManyToMany
    @JoinTable(
            name = ProductFields.PRODUCT_EXTRA_FEATURE_LINK,
            joinColumns = @JoinColumn(name = IdFields.PRODUCT_ID),
            inverseJoinColumns = @JoinColumn(name = IdFields.EXTRA_FEATURE_ID)
    )
    protected Set<ExtraFeature> extraFeatures;

    @Column(name = ProductFields.IMAGE, nullable = false)
    protected String image;

    @ElementCollection
    @CollectionTable(
            name = TableNames.PRODUCT_GALLERIES,
            joinColumns = @JoinColumn(name = IdFields.PRODUCT_ID)
    )
    @Column(name = ProductFields.IMAGE_URL)
    protected List<String> gallery;

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