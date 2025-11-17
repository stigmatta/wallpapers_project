package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.ProductType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WSH_PRODUCT_CATEGORIES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "PRODUCT_TYPE", nullable = false)
    private ProductType productType;
}

