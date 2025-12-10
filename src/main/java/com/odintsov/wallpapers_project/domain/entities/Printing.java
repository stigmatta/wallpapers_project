package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "WSH_PRINTINGS")
public class Printing extends BaseProduct {

    @OneToMany(mappedBy = "printing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrintingMethodsLink> printingLinks;
}
