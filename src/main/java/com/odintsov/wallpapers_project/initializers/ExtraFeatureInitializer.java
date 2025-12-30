package com.odintsov.wallpapers_project.initializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odintsov.wallpapers_project.application.exceptions.EntityNotFoundException;
import com.odintsov.wallpapers_project.domain.entities.ExtraFeature;
import com.odintsov.wallpapers_project.domain.entities.ProductType;
import com.odintsov.wallpapers_project.domain.repositories.ExtraFeatureRepository;
import com.odintsov.wallpapers_project.domain.repositories.ProductTypeRepository;
import com.odintsov.wallpapers_project.initializers.dtos.ExtraFeatureJson;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExtraFeatureInitializer {

    private final ExtraFeatureRepository extraFeatureRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void initExtraFeatures() throws IOException {
        if (extraFeatureRepository.count() > 0) {
            return;
        }

        List<ExtraFeatureJson> data = objectMapper.readValue(
                new ClassPathResource("data/extra_features.json").getInputStream(),
                new TypeReference<>() {
                }
        );

        for (ExtraFeatureJson item : data) {
            ProductType type = productTypeRepository.findByName(item.productType())
                    .orElseThrow(() -> new EntityNotFoundException("Тип продукту не знайдено для доп. послуги: " + item.productType()));

            ExtraFeature feature = ExtraFeature.builder()
                    .name(item.name())
                    .description(item.description())
                    .price(item.price())
                    .productType(type)
                    .build();

            extraFeatureRepository.save(feature);
        }
    }
}
