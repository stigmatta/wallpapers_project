package com.odintsov.wallpapers_project.initializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odintsov.wallpapers_project.application.exceptions.EntityNotFoundException;
import com.odintsov.wallpapers_project.domain.entities.ProductType;
import com.odintsov.wallpapers_project.domain.entities.ProductTypeAttribute;
import com.odintsov.wallpapers_project.domain.repositories.ProductTypeAttributeRepository;
import com.odintsov.wallpapers_project.domain.repositories.ProductTypeRepository;
import com.odintsov.wallpapers_project.initializers.dtos.AttributeJson;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductTypeAttributeInitializer {

    private final ProductTypeAttributeRepository attributeRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void initAttributes() throws IOException {
        if (attributeRepository.count() > 0) {
            return;
        }

        List<AttributeJson> data = objectMapper.readValue(
                new ClassPathResource("data/product_type_attributes.json").getInputStream(),
                new TypeReference<>() {
                }
        );

        for (AttributeJson item : data) {
            ProductType type = productTypeRepository.findByName(item.productType())
                    .orElseThrow(() -> new EntityNotFoundException("Тип продукта не найден: " + item.productType()));

            ProductTypeAttribute attribute = ProductTypeAttribute.builder()
                    .productType(type)
                    .attributeKey(item.attributeKey())
                    .dataType(item.dataType())
                    .isRequired(item.isRequired())
                    .validationRules(item.validationRules())
                    .build();

            attributeRepository.save(attribute);
        }
    }
}
