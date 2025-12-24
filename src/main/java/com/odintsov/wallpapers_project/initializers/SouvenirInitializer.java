package com.odintsov.wallpapers_project.initializers;

import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.entities.Souvenir;
import com.odintsov.wallpapers_project.domain.repositories.CategoryRepository;
import com.odintsov.wallpapers_project.domain.repositories.SouvenirRepository;
import com.odintsov.wallpapers_project.initializers.dtos.SouvenirJson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;



@Component
@RequiredArgsConstructor
public class SouvenirInitializer {

    private final SouvenirRepository souvenirRepository;
    private final CategoryRepository categoryRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void initSouvenirs() throws IOException {
        if (souvenirRepository.count() > 0) {
            return;
        }

        Map<String, Category> catMap = categoryRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Category::getName,
                        c -> c,
                        (existing, duplicate) -> existing
                ));

        List<SouvenirJson> souvenirData = objectMapper.readValue(
                new ClassPathResource("data/souvenirs.json").getInputStream(),
                new TypeReference<>() {
                }
        );

        List<Souvenir> souvenirs = souvenirData.stream().map(data -> {
            List<Category> souvenirCategories = data.categories().stream()
                    .map(catMap::get)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            return Souvenir.builder()
                    .name(data.name())
                    .image(data.image())
                    .article(data.article())
                    .basePrice(data.basePrice())
                    .quantity(data.quantity())
                    .description(data.description())
                    .width(data.width())
                    .length(data.length())
                    .thickness(data.thickness())
                    .categories(souvenirCategories)
                    .build();
        }).collect(Collectors.toList());

        souvenirRepository.saveAll(souvenirs);
        souvenirRepository.flush();
    }


}