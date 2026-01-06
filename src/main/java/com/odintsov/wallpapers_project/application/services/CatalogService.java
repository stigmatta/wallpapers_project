package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.dtos.CategoryResponse;
import com.odintsov.wallpapers_project.application.dtos.ExtraFeatureResponse;
import com.odintsov.wallpapers_project.application.dtos.ProductTypeResponse;
import com.odintsov.wallpapers_project.application.mappers.CatalogMapper;
import com.odintsov.wallpapers_project.domain.repositories.CategoryRepository;
import com.odintsov.wallpapers_project.domain.repositories.ExtraFeatureRepository;
import com.odintsov.wallpapers_project.domain.repositories.ProductTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
    private final CategoryRepository categoryRepository;
    private final ExtraFeatureRepository featureRepository;
    private final ProductTypeRepository productTypeRepository;
    private final CatalogMapper catalogMapper;


    public CatalogService(CategoryRepository categoryRepository, ExtraFeatureRepository featureRepository, ProductTypeRepository productTypeRepository, CatalogMapper catalogMapper) {
        this.categoryRepository = categoryRepository;
        this.featureRepository = featureRepository;
        this.productTypeRepository = productTypeRepository;
        this.catalogMapper = catalogMapper;
    }

    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll().stream()
                .map(catalogMapper::categoryToResponse)
                .toList();
    }

    public List<ExtraFeatureResponse> getFeatures(String productTypeId) {
        return featureRepository.findByProductTypeId(productTypeId).stream().map(catalogMapper::featureToResponse).toList();
    }

    public List<ProductTypeResponse> getProductTypes() {
        return productTypeRepository.findAll().stream().map(catalogMapper::typeToResponse).toList();
    }
}
