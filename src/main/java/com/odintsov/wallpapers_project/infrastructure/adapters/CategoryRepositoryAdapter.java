package com.odintsov.wallpapers_project.infrastructure.adapters;

import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.repositories.CategoryRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.JpaCategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryRepositoryAdapter
        extends BaseJpaRepositoryAdapter<Category, Long, JpaCategoryRepository>
        implements CategoryRepository {

    public CategoryRepositoryAdapter(JpaCategoryRepository jpaRepository) {
        super(jpaRepository);
    }
}
