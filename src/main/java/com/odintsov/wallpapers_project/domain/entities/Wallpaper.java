package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(name = "WSH_WALLPAPERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@SuperBuilder

public class Wallpaper extends BaseProduct {

    @Column(name = "DENSITY", nullable = false)
    private Float density;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "WSH_WALLPAPER_MATERIAL_LINK",
            joinColumns = @JoinColumn(name = "WALLPAPER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MATERIAL_ID")
    )
    private Set<WallpaperMaterial> materials;

    @Column(name = "WATERPROOF", nullable = false)
    private Boolean waterproof;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "WSH_WALLPAPER_ROOM_LINK",
            joinColumns = @JoinColumn(name = "WALLPAPER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROOM_ID")
    )
    private Set<WallpaperRoom> rooms;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "WALLPAPER_CATEGORY_LINK",
            joinColumns = @JoinColumn(name = "WALLPAPER_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")
    )
    protected Set<Category> categories;
}
