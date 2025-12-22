package com.odintsov.wallpapers_project.initializers;

import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryInitializer {
    private final CategoryRepository categoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void initCategories() {
        if (categoryRepository.count() == 0) {
            Category cat1 = new Category();
            cat1.setId(UUID.randomUUID().toString());
            cat1.setName("Decor");
            Category cat2 = new Category();
            cat2.setId(UUID.randomUUID().toString());
            cat2.setName("Gift");
            Category cat3 = new Category();
            cat3.setId(UUID.randomUUID().toString());
            cat3.setName("Vintage");

            categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
            categoryRepository.flush();
        }
    }
}
