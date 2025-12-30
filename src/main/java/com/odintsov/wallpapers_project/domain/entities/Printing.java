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

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = TableNames.PRINTINGS)
public class Printing extends BaseProduct {

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = TableNames.PRINTING_CATEGORY_LINKS,
            joinColumns = @JoinColumn(name = IdFields.PRINTING_ID),
            inverseJoinColumns = @JoinColumn(name = IdFields.CATEGORY_ID)
    )
    protected List<Category> categories;
    @OneToMany(mappedBy = "printing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrintingMethodsLink> printingLinks;

}
