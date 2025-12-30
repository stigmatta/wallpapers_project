package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.ProductFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

/**
 * Entity representing a historical record of a {@link Printing} product within an order.
 * <p>
 * This class is specifically used by {@link OrderHistory} to store the details
 * of printing items purchased in the past. It maintains a snapshot of the
 * price and quantity to ensure historical accuracy even if the product
 * data changes in the future.
 */
@Entity
@Table(name = TableNames.PRINTING_ORDER_ITEMS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PrintingOrderItem {

    /**
     * Unique identifier for the historical printing item.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    @EqualsAndHashCode.Include
    protected String id;

    /**
     * The historical order record this item belongs to.
     */
    @ManyToOne
    @JoinColumn(name = IdFields.ORDER_ID)
    private OrderHistory order;

    /**
     * The printing product that was purchased.
     */
    @ManyToOne
    @JoinColumn(name = IdFields.PRINTING_ID)
    private Printing printing;

    /**
     * The quantity of this specific printing product purchased in the order.
     */
    @Column(name = ProductFields.QUANTITY, nullable = false)
    private Integer quantity;

    /**
     * The price of the product at the time the order was finalized.
     */
    @Column(name = ProductFields.PRICE, nullable = false)
    private Double price;
}