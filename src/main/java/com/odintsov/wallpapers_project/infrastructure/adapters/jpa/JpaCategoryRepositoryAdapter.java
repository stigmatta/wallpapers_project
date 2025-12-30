package com.odintsov.wallpapers_project.infrastructure.adapters.jpa;

import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.repositories.CategoryRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.jpa.JpaCategoryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.lang.model.type.NullType;

@Component
@Profile("jpa")
public class JpaCategoryRepositoryAdapter
        extends BaseJpaRepositoryAdapter<Category, String, NullType, JpaCategoryRepository>
        implements CategoryRepository {

    public JpaCategoryRepositoryAdapter(JpaCategoryRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public Page<Category> filter(NullType filter, Pageable pageable) {
        return jpaRepository.findAll(pageable); //no filtering
    }
}