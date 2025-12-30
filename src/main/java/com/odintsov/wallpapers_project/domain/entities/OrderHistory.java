package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = TableNames.ORDER_HISTORIES)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderHistory {

    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    @EqualsAndHashCode.Include
    protected String id;

    @ManyToOne
    @JoinColumn(name = IdFields.USER_ID)
    private User user;

    @Column(name = CommonFields.CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order")
    private List<SouvenirOrderItem> souvenirItems;

    @OneToMany(mappedBy = "order")
    private List<WallpaperOrderItem> wallpaperItems;
}

