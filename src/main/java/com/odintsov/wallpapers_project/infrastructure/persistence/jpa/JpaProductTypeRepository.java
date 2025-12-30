package com.odintsov.wallpapers_project.infrastructure.persistence.jpa;


import com.odintsov.wallpapers_project.domain.entities.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaProductTypeRepository
        extends JpaRepository<ProductType, Long>, JpaSpecificationExecutor<ProductType> {

    @Query("SELECT u FROM ProductType u WHERE u.name = :name")
    Optional<ProductType> findByName(@Param("name") String name);
}
