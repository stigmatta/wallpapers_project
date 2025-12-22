package com.odintsov.wallpapers_project.initializers;

import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.entities.Souvenir;
import com.odintsov.wallpapers_project.domain.repositories.CategoryRepository;
import com.odintsov.wallpapers_project.domain.repositories.SouvenirRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SouvenirInitializer {

    private final SouvenirRepository souvenirRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void initSouvenirs() {
        if (souvenirRepository.count() > 0) return;

        // 1. Отримуємо категорії. Якщо їх немає - створюємо.
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            categories = categoryRepository.saveAll(List.of(
                    Category.builder().id(UUID.randomUUID().toString()).name("Decor").build(),
                    Category.builder().id(UUID.randomUUID().toString()).name("Gift").build(),
                    Category.builder().id(UUID.randomUUID().toString()).name("Vintage").build()
            ));
            categoryRepository.flush();
        }

        Map<String, Category> catMap = categories.stream()
                .collect(Collectors.toMap(Category::getName, c -> c));

        // 2. Створюємо сувеніри, приєднуючи існуючі категорії
        Souvenir s1 = Souvenir.builder()
                .id(UUID.randomUUID().toString())
                .name("Mini Eiffel Tower")
                .article("SOU-001")
                .basePrice(15.0f)
                .quantity(100)
                .description("Eiffel Tower")
                .width(5.0f).length(5.0f).thickness(10.0f)
                .categories(new ArrayList<>(new HashSet<>(List.of(catMap.get("Decor"), catMap.get("Gift")))))
                .build();

        Souvenir s2 = Souvenir.builder()
                .id(UUID.randomUUID().toString())
                .name("Vintage Postcard")
                .article("SOU-002")
                .basePrice(3.0f)
                .quantity(500)
                .description("Postcard")
                .width(10.0f).length(15.0f).thickness(0.1f)
                .categories(new ArrayList<>(new HashSet<>(List.of(catMap.get("Vintage")))))
                .build();

        // 3. Зберігаємо сувеніри
        souvenirRepository.saveAll(List.of(s1, s2));
        souvenirRepository.flush();

    }
}