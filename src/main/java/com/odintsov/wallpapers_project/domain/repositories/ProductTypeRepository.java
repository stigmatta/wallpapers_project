package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.domain.entities.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProductTypeRepository {
    long count();

    List<ProductType> findAll();

    List<ProductType> saveAll(List<ProductType> types);

    Optional<ProductType> findByName(String name);
}
