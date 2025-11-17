package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "WSH_PRINTING_METHODS_LINK")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrintingMethodsLink {

    @EmbeddedId
    private PrintingMethodsLinkId id;

    @ManyToOne
    @MapsId("printingId")
    @JoinColumn(name = "PRINTING_ID")
    private Printing printing;

    @ManyToOne
    @MapsId("methodId")
    @JoinColumn(name = "METHOD_ID")
    private PrintMethod printMethod;

    public PrintingMethodsLink(Printing printing, PrintMethod printMethod) {
        this.printing = printing;
        this.printMethod = printMethod;
        this.id = new PrintingMethodsLinkId(printing.getId(), printMethod.getId());
    }
}
