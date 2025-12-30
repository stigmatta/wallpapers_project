package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.ProductFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a specific manufacturing or printing technique.
 * <p>
 * This class defines the available methods (e.g., "Offset", "Digital", "Large Format")
 * that can be applied to printing products. It includes production lead times
 * (deadlines) to help manage manufacturing schedules.
 */
@Entity
@Table(name = TableNames.PRINT_METHODS)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class PrintMethod {

    /**
     * Unique identifier for the printing method.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    @EqualsAndHashCode.Include
    protected String id;

    /**
     * The name of the technique (e.g., "Silk Screen").
     */
    @Column(name = CommonFields.NAME, nullable = false)
    private String name;

    /**
     * The estimated number of days required to complete production using this method.
     */
    @Column(name = ProductFields.DEADLINE, nullable = false)
    private Integer deadline;

    /**
     * Collection of links associating this method with various {@link Printing} products.
     */
    @OneToMany(mappedBy = "method")
    @Builder.Default
    private List<PrintingMethodsLink> printingLinks = new ArrayList<>();
}