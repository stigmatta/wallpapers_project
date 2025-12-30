package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.ProductFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

/**
 * Entity representing additional features or options that can be applied to products.
 * <p>
 * Extra features allow for product customization (e.g., special materials,
 * protective coatings). This entity maintains a relationship with order items
 * to track which features were selected at the time of purchase.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = TableNames.EXTRA_FEATURES)
public class ExtraFeature {

    /**
     * Unique identifier for the extra feature, generated as a UUID string.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    protected String id;

    /**
     * Unique display name of the feature (e.g., "Glossy Finish").
     */
    @Column(name = CommonFields.NAME, nullable = false, unique = true)
    private String name;

    /**
     * A detailed explanation of what the feature provides to the customer.
     */
    @Column(name = CommonFields.DESCRIPTION)
    private String description;

    @Column(name = ProductFields.PRICE, nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = IdFields.PRODUCT_TYPE_ID)
    private ProductType productType;
}