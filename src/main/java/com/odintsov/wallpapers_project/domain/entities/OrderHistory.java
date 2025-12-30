package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

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

//    /**
//     * The user to whom this order history belongs.
//     */
//    @ManyToOne
//    @JoinColumn(name = IdFields.USER_ID)
//    private User user;
//
//    @OneToMany(mappedBy = "orderHistory")
//    private List<Order> orders;
}