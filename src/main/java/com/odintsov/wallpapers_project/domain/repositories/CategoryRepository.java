package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.domain.entities.Category;

import javax.lang.model.type.NullType;

public interface CategoryRepository extends CrudRepository<Category, String, NullType> {
}
