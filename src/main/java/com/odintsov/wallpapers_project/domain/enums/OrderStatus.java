package com.odintsov.wallpapers_project.domain.enums;

/**
 * Represents the current lifecycle stage of a customer order.
 * <p>
 * This status is used to track fulfillment progress and control
 * state transitions within the order management system.
 */
public enum OrderStatus {
    /** The order has been placed by the customer but not yet reviewed by staff. */
    NEW,

    /** The order is currently being manufactured, packed, or prepared for shipping. */
    PROCESSING,

    /** The order has been successfully delivered or picked up by the customer. */
    COMPLETED,

    /** The order was terminated either by the customer or the system (e.g., payment failure). */
    CANCELLED
}