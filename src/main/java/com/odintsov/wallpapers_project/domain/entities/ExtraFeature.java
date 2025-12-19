package com.odintsov.wallpapers_project.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "WSH_EXTRA_FEATURES")
public class ExtraFeature {

    @Id
    @UuidGenerator
    @Column(name = "id", updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    protected String id;

    @Column(name = "FEATURE_NAME", nullable = false, unique = true)
    private String featureName;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "extraFeature", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItemExtraFeature> orderItemExtraFeatures = new ArrayList<>();
}

