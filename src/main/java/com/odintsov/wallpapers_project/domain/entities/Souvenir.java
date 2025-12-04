package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WSH_SOUVENIRS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Souvenir extends BaseProduct {

    @Column(nullable = false)
    private Float width;

    @Column(nullable = false)
    private Float length;

    @Column(nullable = false)
    private Float thickness;
}
