package com.odintsov.wallpapers_project.infrastructure.persistence.jpa;

import com.odintsov.wallpapers_project.domain.entities.ExtraFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface JpaExtraFeatureRepository
        extends JpaRepository<ExtraFeature, String>, JpaSpecificationExecutor<ExtraFeature> {

    List<ExtraFeature> findByProductTypeId(String productTypeId);
}
