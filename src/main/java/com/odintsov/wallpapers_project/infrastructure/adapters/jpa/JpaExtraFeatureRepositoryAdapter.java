package com.odintsov.wallpapers_project.infrastructure.adapters.jpa;

import com.odintsov.wallpapers_project.domain.entities.ExtraFeature;
import com.odintsov.wallpapers_project.domain.repositories.ExtraFeatureRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.jpa.JpaExtraFeatureRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Profile("jpa")
public class JpaExtraFeatureRepositoryAdapter implements ExtraFeatureRepository {

    private final JpaExtraFeatureRepository jpaRepository;

    public JpaExtraFeatureRepositoryAdapter(JpaExtraFeatureRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<ExtraFeature> findByProductTypeId(String productTypeId) {
        return jpaRepository.findByProductTypeId(productTypeId);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public ExtraFeature save(ExtraFeature attribute) {
        return jpaRepository.save(attribute);
    }

    @Override
    public void saveAll(List<ExtraFeature> attributes) {
        jpaRepository.saveAll(attributes);
    }

    @Override
    public Optional<ExtraFeature> findById(String id) {
        return jpaRepository.findById(id);
    }
}
