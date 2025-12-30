package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a customer purchase order.
 * <p>
 * This class tracks the lifecycle of an order from creation to fulfillment.
 * It aggregates the total price, maintains the current {@link OrderStatus},
 * and serves as the parent for a collection of {@link OrderItem} records.
 */
@Entity
@Table(name = TableNames.ORDERS)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    /**
     * Unique identifier for the order, generated as a UUID string.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    protected String id;

    /**
     * The customer who placed this order.
     */
    @ManyToOne
    @JoinColumn(name = IdFields.USER_ID)
    private User user;

    /**
     * The total price of the order at the time of purchase.
     */
    @Column(name = ProductFields.PRICE, nullable = false)
    private Double price;

    /**
     * Timestamp indicating when the order was placed.
     * Defaults to the current system time.
     */
    @Column(name = CommonFields.CREATED_AT, nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * The current lifecycle state of the order (e.g., NEW, PAID, SHIPPED).
     */
    @Column(name = CommonFields.STATUS, nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.NEW;

    /**
     * The list of specific products and quantities associated with this order.
     * <p>
     * Managed via composition; deleting an order will delete its associated items.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

}