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
import java.util.List;

@Entity
@Data
@Table(name = TableNames.ORDER_ITEMS)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    protected String id;

    @ManyToOne
    @JoinColumn(name = IdFields.ORDER_ID)
    private Order order;

    @Column(name = CommonFields.PRODUCT_TYPE, nullable = false)
    private String itemType;

    @Column(name = IdFields.PRODUCT_ID, nullable = false)
    private String productId;

    @Column(name = ProductFields.QUANTITY, nullable = false)
    private Integer quantity;

    @Column(name = ProductFields.PRICE, nullable = false)
    private Double price;

    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItemExtraFeature> extraFeatures = new ArrayList<>();
}

