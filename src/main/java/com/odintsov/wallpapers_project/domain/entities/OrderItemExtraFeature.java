package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Join entity that associates a specific {@link OrderItem} with an {@link ExtraFeature}.
 * <p>
 * This entity represents the selection of a customization option for a purchased item.
 * It uses a composite primary key defined in {@link OrderItemExtraFeatureId} and
 * stores the specific configuration value chosen by the user.
 */
@Entity
@Table(name = TableNames.ORDER_ITEM_EXTRA_FEATURES)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(OrderItemExtraFeatureId.class)
public class OrderItemExtraFeature {

    /**
     * The specific order line item this feature is applied to.
     * Part of the composite primary key.
     */
    @Id
    @ManyToOne
    @JoinColumn(name = IdFields.ORDER_ITEM_ID)
    private OrderItem orderItem;

    /**
     * The definition of the extra feature being applied.
     * Part of the composite primary key.
     */
    @Id
    @ManyToOne
    @JoinColumn(name = IdFields.EXTRA_FEATURE_ID)
    private ExtraFeature extraFeature;

    /**
     * The specific input or configuration for this feature.
     * <p>
     * For example, if the feature is "Custom Text", this field would store
     * the string provided by the customer.
     */
    @Column(name = CommonFields.VALUE)
    private String value;
}