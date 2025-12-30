package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.ProductFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = TableNames.WALLPAPER_ORDER_ITEMS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WallpaperOrderItem {

    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    @EqualsAndHashCode.Include
    protected String id;

    @ManyToOne
    @JoinColumn(name = IdFields.ORDER_ID)
    private OrderHistory order;

    @ManyToOne
    @JoinColumn(name = IdFields.WALLPAPER_ID)
    private Wallpaper wallpaper;

    @ManyToOne
    @JoinColumn(name = IdFields.WALLPAPER_MATERIAL_ID)
    private WallpaperMaterial material;

    @Column(name = ProductFields.PRICE, nullable = false)
    private Double price;
}

