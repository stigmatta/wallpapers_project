package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
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

    @ManyToMany
    @JoinTable(
            name = TableNames.PRINTING_METHOD_LINKS,
            joinColumns = @JoinColumn(name = IdFields.PRINTING_ID),
            inverseJoinColumns = @JoinColumn(name = IdFields.PRINT_METHOD_ID)
    )
    @Builder.Default
    private List<PrintMethod> methods = new ArrayList<>();

}