package com.odintsov.wallpapers_project.infrastructure.adapters.jpa;

import com.odintsov.wallpapers_project.domain.entities.ProductType;
import com.odintsov.wallpapers_project.domain.repositories.ProductTypeRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.jpa.JpaProductTypeRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Profile("jpa")
public class JpaProductTypeRepositoryAdapter implements ProductTypeRepository {

    private final JpaProductTypeRepository jpaRepository;

    public JpaProductTypeRepositoryAdapter(JpaProductTypeRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<ProductType> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<ProductType> saveAll(List<ProductType> entities) {
        return jpaRepository.saveAll(entities);
    }

    @Override
    public Optional<ProductType> findByName(String name) {
        return jpaRepository.findByName(name);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }
}
