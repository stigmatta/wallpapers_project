package com.odintsov.wallpapers_project.initializers;

import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CategoryInitializer {
    private final CategoryRepository categoryRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void initCategories() {
        if (categoryRepository.count() == 0) {
            Category cat1 = new Category(); cat1.setName("Decor");
            Category cat2 = new Category(); cat2.setName("Gift");
            Category cat3 = new Category(); cat3.setName("Vintage");

            categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
            categoryRepository.flush();
        }
    }
}
