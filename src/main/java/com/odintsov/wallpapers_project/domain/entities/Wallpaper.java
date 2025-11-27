package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set; // Добавляем Set

@Entity
@Table(name = "WSH_WALLPAPERS")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Wallpaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String name;
    private String image;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private ProductCategory category;

    // --- Добавленное Поле Цены ---
    @Column(nullable = false)
    private Double basePrice;

    // --- Добавленное Отношение Many-to-Many ---
    @ManyToMany
    @JoinTable(
            name = "WSH_WALLPAPER_MATERIAL_LINK",
            joinColumns = @JoinColumn(name = "WALLPAPER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MATERIAL_ID")
    )
    private Set<WallpaperMaterial> availableMaterials;
}