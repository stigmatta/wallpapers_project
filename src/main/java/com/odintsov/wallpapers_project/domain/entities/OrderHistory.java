package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "WSH_ORDER_HISTORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderHistory {

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    @EqualsAndHashCode.Include
    protected String id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order")
    private List<SouvenirOrderItem> souvenirItems;

    @OneToMany(mappedBy = "order")
    private List<WallpaperOrderItem> wallpaperItems;
}

