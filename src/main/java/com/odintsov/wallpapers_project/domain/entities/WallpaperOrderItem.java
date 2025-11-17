package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WSH_WALLPAPER_ORDER_ITEMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WallpaperOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private OrderHistory order;

    @ManyToOne
    @JoinColumn(name = "WALLPAPER_ID")
    private Wallpaper wallpaper;

    @ManyToOne
    @JoinColumn(name = "MATERIAL_ID")
    private WallpaperMaterial material;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double width;

    @Column(nullable = false)
    private Double height;
}

