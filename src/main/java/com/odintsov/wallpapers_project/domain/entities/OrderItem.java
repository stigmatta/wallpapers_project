package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.ProductFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Entity representing a specific line item within a customer's {@link Order}.
 * <p>
 * This class captures a snapshot of the product details at the time of purchase,
 * including the price, quantity, and any applied customizations (extra features).
 * It uses a polymorphic approach to reference different product types via
 * {@code itemType} and {@code productId}.
 */
@Entity
@Data
@Table(name = TableNames.ORDER_ITEMS)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    /**
     * Unique identifier for the order item, generated as a UUID string.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    protected String id;

    /**
     * The parent order that this item belongs to.
     */
    @ManyToOne
    @JoinColumn(name = IdFields.ORDER_ID)
    private Order order;

    @ManyToOne
    @JoinColumn(name = IdFields.ORDER_HISTORY_ID)
    private OrderHistory orderHistory;

    /**
     * The category or class name of the product (e.g., "WALLPAPER", "SOUVENIR").
     * Used to determine which service or repository to use when fetching product details.
     */
    @Column(name = CommonFields.PRODUCT_TYPE, nullable = false)
    private String productType;

    /**
     * The unique identifier of the product in its respective table.
     */
    @Column(name = IdFields.PRODUCT_ID, nullable = false)
    private String productId;

    /**
     * The number of units of the product purchased.
     */
    @Column(name = ProductFields.QUANTITY, nullable = false)
    private Integer quantity;

    /**
     * The unit price of the product at the moment the order was placed.
     */
    @Column(name = ProductFields.PRICE, nullable = false)
    private Double price;

    /**
     * Custom features or add-ons selected by the user for this specific item.
     * <p>
     * Managed via composition; these features are deleted if the item is removed.
     */
    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItemExtraFeature> orderItemExtraFeatures = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = TableNames.ORDER_ITEM_SPECIFICATIONS,
            joinColumns = @JoinColumn(name = IdFields.ORDER_ITEM_ID)
    )
    @MapKeyColumn(name = ProductFields.SPEC_KEY)
    @Column(name = ProductFields.SPEC_VALUE)
    @Builder.Default
    private Map<String, String> specifications = new HashMap<>();
}