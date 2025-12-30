package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.domain.entities.ExtraFeature;

import java.util.List;
import java.util.Optional;

public interface ExtraFeatureRepository {
    long count();

    ExtraFeature save(ExtraFeature attribute);

    void saveAll(List<ExtraFeature> attributes);

    Optional<ExtraFeature> findById(String id);

    List<ExtraFeature> findByProductTypeId(String productTypeId);
}
