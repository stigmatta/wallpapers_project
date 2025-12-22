package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PrintingMethodsLinkId implements Serializable {
    private String printingId;
    private String methodId;
}
