package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WSH_WALLPAPER_MATERIAL_LINK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WallpaperMaterialLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "WALLPAPER_ID")
    private Wallpaper wallpaper;

    @ManyToOne
    @JoinColumn(name = "MATERIAL_ID")
    private WallpaperMaterial material;
}

