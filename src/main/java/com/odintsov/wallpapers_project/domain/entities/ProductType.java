package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a distinct type of product (e.g., 'Wallpapers', 'Souvenirs').
 * Maps to a corresponding database table (often WSH_PRODUCT_TYPES, though not explicitly defined here).
 * This entity is typically used to classify categories or other product-related entities.
 */
@Entity
@Table(name = "WSH_PRODUCT_TYPES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductType {

    /**
     * The unique identifier for the product type.
     * Primary key with identity generation strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * The name or identifier of the product type (e.g., "WALLPAPERS", "SOUVENIRS").
     * This field is typically unique and non-nullable.
     */
    @Column(nullable = false, unique = true)
    private String name;
}