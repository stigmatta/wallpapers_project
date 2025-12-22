package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

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

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "SOUVENIR_CATEGORY_LINK",
            joinColumns = @JoinColumn(name = "SOUVENIR_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")
    )
    protected List<Category> categories;
}
