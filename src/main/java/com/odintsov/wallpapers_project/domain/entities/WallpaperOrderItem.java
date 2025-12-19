package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "WSH_WALLPAPER_ORDER_ITEMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WallpaperOrderItem {

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    @EqualsAndHashCode.Include
    protected String id;

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

