package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "WSH_PRINTING_METHODS_LINK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrintingMethodsLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // можно вообще без своего ID, но с ним проще

    @ManyToOne
    @JoinColumn(name = "printing_id", nullable = false)
    private Printing printing;

    @ManyToOne
    @JoinColumn(name = "method_id", nullable = false)
    private PrintMethod method;
}