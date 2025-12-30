package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

/**
 * Represents a distinct type of product (e.g., 'Wallpapers', 'Souvenirs').
 * Maps to a corresponding database table (often WSH_PRODUCT_TYPES, though not explicitly defined here).
 * This entity is typically used to classify categories or other product-related entities.
 */
@Entity
@Table(name = TableNames.PRODUCT_TYPES)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class ProductType {

    /**
     * The unique identifier for the product type.
     * Primary key with identity generation strategy.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    @EqualsAndHashCode.Include
    protected String id;
    /**
     * The name or identifier of the product type (e.g., "WALLPAPERS", "SOUVENIRS").
     * This field is typically unique and non-nullable.
     */
    @Column(name = CommonFields.NAME, nullable = false, unique = true)
    private String name;
}