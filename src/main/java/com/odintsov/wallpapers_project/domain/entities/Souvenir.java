package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.SouvenirFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
     * The horizontal dimension of the souvenir in centimeters or millimeters.
     */
    @Column(name = SouvenirFields.WIDTH, nullable = false)
    private Double width;

    /**
     * The vertical dimension of the souvenir.
     */
    @Column(name = SouvenirFields.LENGTH, nullable = false)
    private Double length;

    /**
     * The depth or thickness of the souvenir material.
     */
    @Column(name = SouvenirFields.THICKNESS, nullable = false)
    private Double thickness;
}