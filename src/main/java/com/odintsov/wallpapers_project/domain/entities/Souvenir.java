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

@Entity
@Table(name = TableNames.SOUVENIRS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Souvenir extends BaseProduct {

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = TableNames.SOUVENIR_CATEGORY_LINKS,
            joinColumns = @JoinColumn(name = IdFields.SOUVENIR_ID),
            inverseJoinColumns = @JoinColumn(name = IdFields.CATEGORY_ID)
    )
    protected List<Category> categories;
    @Column(name = SouvenirFields.WIDTH, nullable = false)
    private Float width;
    @Column(name = SouvenirFields.LENGTH, nullable = false)
    private Float length;
    @Column(name = SouvenirFields.THICKNESS, nullable = false)
    private Float thickness;
}
