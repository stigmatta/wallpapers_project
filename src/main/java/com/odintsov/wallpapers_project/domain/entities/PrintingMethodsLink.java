package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

/**
 * Entity representing the association between a {@link Printing} product
 * and a specific {@link PrintMethod}.
 * <p>
 * This link determines which technical methods (e.g., UV Printing,
 * Laser Engraving) are valid for a particular product instance.
 */
@Entity
@Table(name = TableNames.PRINTING_METHOD_LINKS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrintingMethodsLink {

    /**
     * Unique identifier for the association record.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    protected String id;

    /**
     * The printing product being associated with a method.
     */
    @ManyToOne
    @JoinColumn(name = IdFields.PRINTING_ID, nullable = false)
    private Printing printing;

    /**
     * The specific printing technique or method being assigned to the product.
     */
    @ManyToOne
    @JoinColumn(name = IdFields.PRINT_METHOD_ID, nullable = false)
    private PrintMethod method;
}