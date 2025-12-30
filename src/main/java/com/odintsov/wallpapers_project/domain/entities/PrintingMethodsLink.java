package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = TableNames.PRINTING_METHOD_LINKS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrintingMethodsLink {

    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    protected String id;

    @ManyToOne
    @JoinColumn(name = IdFields.PRINTING_ID, nullable = false)
    private Printing printing;

    @ManyToOne
    @JoinColumn(name = IdFields.PRINT_METHOD_ID, nullable = false)
    private PrintMethod method;
}