package com.odintsov.wallpapers_project.initializers;

import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.entities.Souvenir;
import com.odintsov.wallpapers_project.domain.repositories.CategoryRepository;
import com.odintsov.wallpapers_project.domain.repositories.SouvenirRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SouvenirInitializer {

    private final SouvenirRepository souvenirRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void initSouvenirs() {
        if (souvenirRepository.count() > 0) {
            return;
        }

        List<Category> categories = createCategories();

        List<Souvenir> souvenirs = createSouvenirs(categories);

        souvenirRepository.saveAll(souvenirs);
    }

    private List<Category> createCategories() {
        if (categoryRepository.count() > 0) {
            return categoryRepository.findAll();
        }

        Category cat1 = new Category();
        cat1.setName("Decor");
        Category cat2 = new Category();
        cat2.setName("Gift");
        Category cat3 = new Category();
        cat3.setName("Vintage");

        return categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
    }

    private List<Souvenir> createSouvenirs(List<Category> categories) {
        Category decor = categories.get(0);
        Category gift = categories.get(1);
        Category vintage = categories.get(2);

        Souvenir s1 = new Souvenir();
        s1.setName("Mini Eiffel Tower");
        s1.setArticle("SOU-001");
        s1.setBasePrice(15.0f);
        s1.setSalePrice(12.5f);
        s1.setImage("/images/eiffel.jpg");
        s1.setDescription("Small souvenir of Eiffel Tower");
        s1.setQuantity(200);
        s1.setCategories(new HashSet<>(Arrays.asList(decor, gift)));
        s1.setWidth(5.0f);
        s1.setLength(5.0f);
        s1.setThickness(10.0f);

        Souvenir s2 = new Souvenir();
        s2.setName("Vintage Postcard");
        s2.setArticle("SOU-002");
        s2.setBasePrice(3.0f);
        s2.setSalePrice(2.5f);
        s2.setImage("/images/postcard.jpg");
        s2.setDescription("Old-style vintage postcard");
        s2.setQuantity(500);
        s2.setCategories(new HashSet<>(List.of(vintage)));
        s2.setWidth(10.0f);
        s2.setLength(15.0f);
        s2.setThickness(0.5f);

        return Arrays.asList(s1, s2);
    }
}
