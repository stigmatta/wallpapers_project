package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.ProductFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

/**
 * Entity representing a historical record of a {@link Souvenir} product within an order.
 * <p>
 * This class is used by {@link OrderHistory} to persist the state of souvenir items
 * at the time of purchase. It maintains immutable records of the price and quantity
 * to provide an accurate audit trail for customer purchase history.
 */
@Entity
@Table(name = TableNames.SOUVENIR_ORDER_ITEMS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SouvenirOrderItem {

    /**
     * Unique identifier for the historical souvenir item record.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    @EqualsAndHashCode.Include
    protected String id;

    /**
     * The historical order record to which this souvenir item belongs.
     */
    @ManyToOne
    @JoinColumn(name = IdFields.ORDER_ID)
    private OrderHistory order;

    /**
     * The specific souvenir product that was purchased.
     */
    @ManyToOne
    @JoinColumn(name = IdFields.SOUVENIR_ID)
    private Souvenir souvenir;

    /**
     * The number of souvenir units purchased in this transaction.
     */
    @Column(name = ProductFields.QUANTITY, nullable = false)
    private Integer quantity;

    /**
     * The unit price of the souvenir at the time the order was finalized.
     * Stored separately to protect history from future catalog price changes.
     */
    @Column(name = ProductFields.PRICE, nullable = false)
    private Double price;
}