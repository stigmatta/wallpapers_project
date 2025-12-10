package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class BaseProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected String description;

    @Column(nullable = false, unique = true)
    protected String article;

    @ManyToMany
    @JoinTable(
            name = "PRODUCT_CATEGORY_LINK",
            joinColumns = @JoinColumn(name = "PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")
    )
    protected Set<Category> categories;

    @Column(nullable = false)
    protected Float basePrice;

    protected Float salePrice;

    protected Float rating;

    @Column(nullable = false)
    protected Integer quantity;

    @ManyToMany
    @JoinTable(
            name = "PRODUCT_EXTRA_FEATURE_LINK",
            joinColumns = @JoinColumn(name = "PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name = "EXTRA_FEATURE_ID")
    )
    protected Set<ExtraFeature> extraFeatures;

    protected String image;

    @ElementCollection
    @CollectionTable(
            name = "PRODUCT_GALLERY",
            joinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    @Column(name = "IMAGE_URL")
    protected List<String> gallery;
}
