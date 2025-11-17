package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WSH_WALLPAPERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}

