package com.odintsov.wallpapers_project.infrastructure.adapters;

import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.repositories.CategoryRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.JpaCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.lang.model.type.NullType;

@Component
public class CategoryRepositoryAdapter
        extends BaseJpaRepositoryAdapter<Category, String, NullType, JpaCategoryRepository>
        implements CategoryRepository {

    public CategoryRepositoryAdapter(JpaCategoryRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public Page<Category> filter(NullType filter, Pageable pageable) {
        return jpaRepository.findAll(pageable); //no filtering
    }}