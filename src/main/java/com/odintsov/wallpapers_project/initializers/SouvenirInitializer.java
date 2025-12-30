package com.odintsov.wallpapers_project.initializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.entities.ProductType;
import com.odintsov.wallpapers_project.domain.entities.Souvenir;
import com.odintsov.wallpapers_project.domain.enums.ProductTypes;
import com.odintsov.wallpapers_project.domain.repositories.CategoryRepository;
import com.odintsov.wallpapers_project.domain.repositories.ProductRepository;
import com.odintsov.wallpapers_project.domain.repositories.ProductTypeRepository;
import com.odintsov.wallpapers_project.domain.repositories.SouvenirRepository;
import com.odintsov.wallpapers_project.initializers.dtos.SouvenirJson;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class SouvenirInitializer {

    private final SouvenirRepository souvenirRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void initSouvenirs() throws IOException {
        if (souvenirRepository.count() > 0) {
            return;
        }

        ProductType souvenirType = productTypeRepository.findByName(ProductTypes.SOUVENIR)
                .orElseThrow(() -> new RuntimeException("ProductType not found in DB!"));

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
                    .productType(souvenirType)
                    .image(data.image())
                    .article(data.article())
                    .price(data.basePrice())
                    .quantity(data.quantity())
                    .description(data.description())
                    .width(data.width())
                    .length(data.length())
                    .thickness(data.thickness())
                    .categories(souvenirCategories)
                    .build();
        }).collect(Collectors.toList());

        productRepository.saveAll(souvenirs);
    }


}