package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.ProductFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.enums.WallpaperFields;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

/**
 * Entity representing the material type for wallpapers.
 * <p>
 * This class defines the physical properties and commercial impact of a material.
 * The {@code priceMultiplier} is used in pricing logic to adjust the base product
 * price according to the material's premium or standard status.
 */
@Entity
@Table(name = TableNames.WALLPAPER_MATERIALS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WallpaperMaterial {

    /**
     * Unique identifier for the material, generated as a UUID string.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    @EqualsAndHashCode.Include
    protected String id;
    /**
     * Primary display image URL.
     */
    @Column(name = ProductFields.IMAGE, nullable = false)
    protected String image;
    /**
     * Unique name of the material (e.g., "Premium Silk", "Standard Paper").
     */
    @Column(name = CommonFields.NAME, nullable = false, unique = true)
    private String name;
    @Column(name = CommonFields.DESCRIPTION, nullable = false, unique = true)
    private String description;
    /**
     * A coefficient used to calculate the final price of the wallpaper.
     * <p>
     * For example, a multiplier of 1.2 adds a 20% premium to the base price.
     */
    @Column(name = WallpaperFields.PRICE_MULTIPLIER)
    private Double priceMultiplier;
}