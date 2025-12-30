package com.odintsov.wallpapers_project.infrastructure.adapters.jpa;

import com.odintsov.wallpapers_project.domain.entities.ProductTypeAttribute;
import com.odintsov.wallpapers_project.domain.repositories.ProductTypeAttributeRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.jpa.JpaProductTypeAttributeRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Profile("jpa")
public class JpaProductTypeAttributeRepositoryAdapter implements ProductTypeAttributeRepository {

    private final JpaProductTypeAttributeRepository jpaRepository;

    public JpaProductTypeAttributeRepositoryAdapter(JpaProductTypeAttributeRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<ProductTypeAttribute> findByProductTypeId(String productTypeId) {
        return jpaRepository.findByProductTypeId(productTypeId);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public ProductTypeAttribute save(ProductTypeAttribute attribute) {
        return jpaRepository.save(attribute);
    }

    @Override
    public void saveAll(List<ProductTypeAttribute> attributes) {
        jpaRepository.saveAll(attributes);
    }
}