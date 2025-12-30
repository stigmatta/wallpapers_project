package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.ProductFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

/**
 * Entity representing a historical record of a {@link Wallpaper} product within an order.
 * <p>
 * This class persists the specific configuration of a wallpaper at the time of purchase,
 * including the chosen {@link WallpaperMaterial}. This ensures that historical records
 * remain accurate even if the wallpaper design or material costs are updated in the catalog.
 */
@Entity
@Table(name = TableNames.WALLPAPER_ORDER_ITEMS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class WallpaperOrderItem {

    /**
     * Unique identifier for the historical wallpaper item.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    @EqualsAndHashCode.Include
    protected String id;

    /**
     * The historical order record this item is associated with.
     */
    @ManyToOne
    @JoinColumn(name = IdFields.ORDER_ID)
    private OrderHistory order;

    /**
     * The specific wallpaper design that was purchased.
     */
    @ManyToOne
    @JoinColumn(name = IdFields.WALLPAPER_ID)
    private Wallpaper wallpaper;

    /**
     * The specific material chosen for this wallpaper instance at the time of order.
     * This reference helps track production specifications and pricing logic.
     */
    @ManyToOne
    @JoinColumn(name = IdFields.WALLPAPER_MATERIAL_ID)
    private WallpaperMaterial material;

    /**
     * The final calculated price of the item at the time the order was placed.
     */
    @Column(name = ProductFields.PRICE, nullable = false)
    private Double price;
}