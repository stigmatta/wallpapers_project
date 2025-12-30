package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Entity representing a printing product in the catalog.
 * <p>
 * Inherits core product attributes from {@link BaseProduct} and adds
 * specific associations for {@link Category} classifications and
 * available {@link PrintingMethodsLink} configurations.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Table(name = TableNames.PRINTINGS)
public class Printing extends BaseProduct {

    /**
     * The list of categories assigned to this printing product.
     * <p>
     * Uses a join table to manage the many-to-many relationship. Cascades
     * {@code MERGE} and {@code PERSIST} to ensure categories are handled
     * correctly during product updates.
     */
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = TableNames.PRINTING_CATEGORY_LINKS,
            joinColumns = @JoinColumn(name = IdFields.PRINTING_ID),
            inverseJoinColumns = @JoinColumn(name = IdFields.CATEGORY_ID)
    )
    protected List<Category> categories;

    /**
     * Detailed links to the various methods available for this specific printing product.
     * <p>
     * This collection manages the relationship with specific techniques,
     * potentially including pricing or material variations per method.
     */
    @OneToMany(mappedBy = "printing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrintingMethodsLink> printingLinks;

}