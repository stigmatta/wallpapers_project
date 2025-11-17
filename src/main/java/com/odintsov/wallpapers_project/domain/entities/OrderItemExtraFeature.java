package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "WSH_ORDER_ITEM_EXTRA_FEATURES", schema = "SYS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderItemExtraFeatureId.class)
public class OrderItemExtraFeature {

    @Id
    @ManyToOne
    @JoinColumn(name = "ORDER_ITEM_ID")
    private OrderItem orderItem;

    @Id
    @ManyToOne
    @JoinColumn(name = "EXTRA_FEATURE_ID")
    private ExtraFeature extraFeature;

    @Column(name = "VALUE")
    private String value;
}
