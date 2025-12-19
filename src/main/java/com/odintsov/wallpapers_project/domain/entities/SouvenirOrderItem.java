package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "WSH_SOUVENIR_ORDER_ITEMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SouvenirOrderItem {

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    @EqualsAndHashCode.Include
    protected String id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private OrderHistory order;

    @ManyToOne
    @JoinColumn(name = "SOUVENIR_ID")
    private Souvenir souvenir;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;
}

