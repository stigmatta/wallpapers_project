package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity representing a historical record of a completed or archived order.
 * <p>
 * Unlike the active {@link Order} entity, {@code OrderHistory} provides a
 * consolidated view of a user's past purchases, categorizing purchased
 * items into specific types (Wallpapers vs. Souvenirs) for reporting and
 * display purposes.
 */
@Entity
@Table(name = TableNames.ORDER_HISTORIES)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderHistory {

    /**
     * Unique identifier for the history record, generated as a UUID string.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    @EqualsAndHashCode.Include
    protected String id;

    /**
     * The user to whom this order history belongs.
     */
    @ManyToOne
    @JoinColumn(name = IdFields.USER_ID)
    private User user;

    /**
     * The timestamp of when the original order was created or archived.
     */
    @Column(name = CommonFields.CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * Collection of souvenir products associated with this historical order.
     */
    @OneToMany(mappedBy = "order")
    private List<SouvenirOrderItem> souvenirItems;

    /**
     * Collection of wallpaper products associated with this historical order.
     */
    @OneToMany(mappedBy = "order")
    private List<WallpaperOrderItem> wallpaperItems;
}