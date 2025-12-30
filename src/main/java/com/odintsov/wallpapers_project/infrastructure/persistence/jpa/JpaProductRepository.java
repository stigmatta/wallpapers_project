package com.odintsov.wallpapers_project.infrastructure.persistence.jpa;

import com.odintsov.wallpapers_project.domain.entities.BaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface JpaProductRepository
        extends JpaRepository<BaseProduct, String>, JpaSpecificationExecutor<BaseProduct> {
    Optional<BaseProduct> findBySlug(String slug);
}

