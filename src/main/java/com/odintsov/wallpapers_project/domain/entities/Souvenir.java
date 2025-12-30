package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.SouvenirFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Entity representing a souvenir product in the catalog.
 * <p>
 * This class extends {@link BaseProduct} to include physical dimensions
 * specific to souvenir items (e.g., mugs, frames, or small decor). It also
 * maintains its own set of category associations.
 */
@Entity
@Table(name = TableNames.SOUVENIRS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Souvenir extends BaseProduct {

    /**
     * The list of categories assigned to this souvenir.
     * <p>
     * Managed through a dedicated join table. Uses {@code MERGE} and
     * {@code PERSIST} cascades to handle category mapping updates.
     */
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = TableNames.SOUVENIR_CATEGORY_LINKS,
            joinColumns = @JoinColumn(name = IdFields.SOUVENIR_ID),
            inverseJoinColumns = @JoinColumn(name = IdFields.CATEGORY_ID)
    )
    protected List<Category> categories;

    /**
     * The horizontal dimension of the souvenir in centimeters or millimeters.
     */
    @Column(name = SouvenirFields.WIDTH, nullable = false)
    private Float width;

    /**
     * The vertical dimension of the souvenir.
     */
    @Column(name = SouvenirFields.LENGTH, nullable = false)
    private Float length;

    /**
     * The depth or thickness of the souvenir material.
     */
    @Column(name = SouvenirFields.THICKNESS, nullable = false)
    private Float thickness;
}