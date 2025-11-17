package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WSH_SOUVENIR_ORDER_ITEMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SouvenirOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

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

