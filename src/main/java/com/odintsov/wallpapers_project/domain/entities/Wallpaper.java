package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.enums.WallpaperFields;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = TableNames.WALLPAPERS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@SuperBuilder

public class Wallpaper extends BaseProduct {

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = TableNames.WALLPAPER_CATEGORY_LINKS,
            joinColumns = @JoinColumn(name = IdFields.WALLPAPER_ID),
            inverseJoinColumns = @JoinColumn(name = IdFields.CATEGORY_ID)
    )
    protected List<Category> categories;
    @Column(name = WallpaperFields.DENSITY, nullable = false)
    private Float density;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = TableNames.WALLPAPER_MATERIAL_LINKS,
            joinColumns = @JoinColumn(name = IdFields.WALLPAPER_ID),
            inverseJoinColumns = @JoinColumn(name = IdFields.CATEGORY_ID)
    )
    private List<WallpaperMaterial> materials;
    @Column(name = WallpaperFields.WATERPROOF, nullable = false)
    private Boolean waterproof;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = TableNames.WALLPAPER_ROOM_LINKS,
            joinColumns = @JoinColumn(name = IdFields.WALLPAPER_ID),
            inverseJoinColumns = @JoinColumn(name = IdFields.ROOM_ID)
    )
    private List<WallpaperRoom> rooms;
}
