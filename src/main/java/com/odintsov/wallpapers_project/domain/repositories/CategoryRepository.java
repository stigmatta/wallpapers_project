package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.domain.entities.Category;

import javax.lang.model.type.NullType;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, String, NullType> {
    List<Category> findByProductTypeId(String productTypeId);

    Optional<Category> findByName(String name);
}
