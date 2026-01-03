package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.dtos.CategoryResponse;
import com.odintsov.wallpapers_project.application.exceptions.EntityNotFoundException;
import com.odintsov.wallpapers_project.application.mappers.CategoryMapper;
import com.odintsov.wallpapers_project.application.mappers.common.DtoMapper;
import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.repositories.CategoryRepository;
import com.odintsov.wallpapers_project.domain.repositories.CrudRepository;
import com.odintsov.wallpapers_project.infrastructure.utils.ProductTypeRegistry;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseProductService<T, ID, F, LR, DR, R extends CrudRepository<T, ID, F>>
        extends BaseCrudService<T, ID, F, LR, DR, R> {

    protected final String productType;
    @Autowired
    protected CategoryRepository categoryRepository;
    @Autowired
    protected CategoryMapper categoryMapper;
    @Autowired
    protected ProductTypeRegistry productTypeRegistry;

    protected BaseProductService(R repository,
                                 DtoMapper<T, LR, DR> mapper,
                                 String productType) {
        super(repository, mapper);
        this.productType = productType;
    }

    public DR findBySlug(String slug) {
        T entity = repository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with slug: " + slug));
        return mapper.toDetailedResponseDto(entity);
    }

    public List<CategoryResponse> getAvailableCategories() {
        String typeId = productTypeRegistry.getTypeId(this.productType);
        List<Category> categories = categoryRepository.findByProductTypeId(typeId);
        return categoryMapper.toResponseList(categories);
    }
}