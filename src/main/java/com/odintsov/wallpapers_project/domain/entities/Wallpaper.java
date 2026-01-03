package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.enums.WallpaperFields;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Entity representing a wallpaper product in the catalog.
 * <p>
 * This class extends {@link BaseProduct} to include technical wallpaper
 * specifications such as density and waterproof status. It also maintains
 * multi-faceted relationships for classification by room type and material.
 */
@Entity
@Table(name = TableNames.WALLPAPERS)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@SuperBuilder
public class Wallpaper extends BaseProduct {

    /**
     * The thickness or weight of the wallpaper material, typically measured in g/m².
     */
    @Column(name = WallpaperFields.DENSITY, nullable = false)
    private Float density;

    /**
     * The types of material this wallpaper is composed of (e.g., "Vinyl", "Non-woven").
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = TableNames.WALLPAPER_MATERIAL_LINKS,
            joinColumns = @JoinColumn(name = IdFields.WALLPAPER_ID),
            inverseJoinColumns = @JoinColumn(name = IdFields.CATEGORY_ID)
    )
    private List<WallpaperMaterial> materials;

    /**
     * Indicates whether the wallpaper is suitable for high-humidity environments.
     */
    @Column(name = WallpaperFields.WATERPROOF, nullable = false)
    private Boolean waterproof;

    /**
     * Suggestions for which rooms this wallpaper is designed for (e.g., "Living Room", "Bathroom").
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = TableNames.WALLPAPER_ROOM_LINKS,
            joinColumns = @JoinColumn(name = IdFields.WALLPAPER_ID),
            inverseJoinColumns = @JoinColumn(name = IdFields.ROOM_ID)
    )
    private List<WallpaperRoom> rooms;
}