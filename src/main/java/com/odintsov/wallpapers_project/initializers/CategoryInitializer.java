package com.odintsov.wallpapers_project.initializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.repositories.CategoryRepository;
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
    private final ObjectMapper objectMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void initCategories() throws IOException {
        if (categoryRepository.count() > 0) {
            return;
        }

        List<Category> categories = objectMapper.readValue(
                new ClassPathResource("data/categories.json").getInputStream(),
                new TypeReference<>() {}
        );

        categoryRepository.saveAll(categories);
        categoryRepository.flush();
    }
}
