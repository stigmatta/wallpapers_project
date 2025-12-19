package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "WSH_WALLPAPER_MATERIALS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WallpaperMaterial {

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    @EqualsAndHashCode.Include
    protected String id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "PRICE_MULTIPLIER")
    private Double priceMultiplier;
}

