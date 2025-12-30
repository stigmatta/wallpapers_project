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
 * Entity representing an interior room classification for wallpapers.
 * <p>
 * This entity allows wallpapers to be categorized by their intended usage
 * environment (e.g., "Bedroom", "Office"). It is used primarily for
 * filtering and faceted search in the storefront.
 */
@Entity
@Table(name = TableNames.WALLPAPER_ROOMS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WallpaperRoom {

    /**
     * Unique identifier for the room type, generated as a UUID string.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    @EqualsAndHashCode.Include
    protected String id;

    /**
     * Unique name of the room type (e.g., "Kitchen", "Children's Room").
     */
    @Column(name = CommonFields.NAME, nullable = false, unique = true)
    private String name;
}