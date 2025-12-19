package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "WSH_PRINTING_METHODS_LINK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrintingMethodsLink {

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    protected String id;

    @ManyToOne
    @JoinColumn(name = "printing_id", nullable = false)
    private Printing printing;

    @ManyToOne
    @JoinColumn(name = "method_id", nullable = false)
    private PrintMethod method;
}