package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "WSH_SOUVENIRS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Souvenir extends BaseProduct {

    @Column(nullable = false)
    private Float width;

    @Column(nullable = false)
    private Float length;

    @Column(nullable = false)
    private Float thickness;
}
