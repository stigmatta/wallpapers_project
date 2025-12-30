package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.domain.entities.ProductTypeAttribute;

import java.util.List;

public interface ProductTypeAttributeRepository {
    long count();

    ProductTypeAttribute save(ProductTypeAttribute attribute);

    void saveAll(List<ProductTypeAttribute> attributes);

    List<ProductTypeAttribute> findByProductTypeId(String productTypeId);
}
