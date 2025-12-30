package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableNames.ORDER_ITEM_EXTRA_FEATURES)
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderItemExtraFeatureId.class)
public class OrderItemExtraFeature {

    @Id
    @ManyToOne
    @JoinColumn(name = IdFields.ORDER_ITEM_ID)
    private OrderItem orderItem;

    @Id
    @ManyToOne
    @JoinColumn(name = IdFields.EXTRA_FEATURE_ID)
    private ExtraFeature extraFeature;

    @Column(name = CommonFields.VALUE)
    private String value;
}
