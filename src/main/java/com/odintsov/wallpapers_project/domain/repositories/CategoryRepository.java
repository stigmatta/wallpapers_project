package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.domain.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {}
