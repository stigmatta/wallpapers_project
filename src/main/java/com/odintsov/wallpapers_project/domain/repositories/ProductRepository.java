package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.application.dtos.Product.ProductFilter;
import com.odintsov.wallpapers_project.domain.entities.BaseProduct;

import java.util.List;

public interface ProductRepository
        extends CrudRepository<BaseProduct, String, ProductFilter>,
        SlugRepository<BaseProduct> {
    void saveAll(List<? extends BaseProduct> entities);

    List<BaseProduct> findGlobal(String name);
}
