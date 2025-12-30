package com.odintsov.wallpapers_project.infrastructure.persistence.jpa;

import com.odintsov.wallpapers_project.domain.entities.ProductTypeAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface JpaProductTypeAttributeRepository
        extends JpaRepository<ProductTypeAttribute, String>, JpaSpecificationExecutor<ProductTypeAttribute> {

    List<ProductTypeAttribute> findByProductTypeId(String productTypeId);
}
