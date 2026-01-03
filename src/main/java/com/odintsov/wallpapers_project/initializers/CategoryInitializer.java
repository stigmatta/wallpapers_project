package com.odintsov.wallpapers_project.initializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odintsov.wallpapers_project.application.exceptions.EntityNotFoundException;
import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.entities.ProductType;
import com.odintsov.wallpapers_project.domain.repositories.CategoryRepository;
import com.odintsov.wallpapers_project.domain.repositories.ProductTypeRepository;
import com.odintsov.wallpapers_project.initializers.dtos.CategoryJson;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryInitializer {
    private final CategoryRepository categoryRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ObjectMapper objectMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void initCategories() throws IOException {
        if (categoryRepository.count() > 0) return;

        List<CategoryJson> data = objectMapper.readValue(
                new ClassPathResource("data/categories.json").getInputStream(),
                new TypeReference<>() {
                }
        );

        List<Category> categories = data.stream().map(json -> {
            ProductType type = productTypeRepository.findByName(json.productType())
                    .orElseThrow(() -> new EntityNotFoundException("Type not found: " + json.productType()));

            return Category.builder()
                    .name(json.name())
                    .productType(type)
                    .build();
        }).toList();

        categoryRepository.saveAll(categories);
    }
}
