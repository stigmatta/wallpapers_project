package com.odintsov.wallpapers_project.domain.entities;

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
    @Column(name = "id", updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    protected String id;

    @Column(nullable = false)
    protected String name;

    // --- NEW FIELD ---
    @Column(nullable = false, unique = true)
    protected String slug;
    // -----------------

    @Column(nullable = false)
    protected String description;

    @Column(nullable = false, unique = true)
    protected String article;

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

    // Optional: Helper to auto-generate slug from name if missing
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