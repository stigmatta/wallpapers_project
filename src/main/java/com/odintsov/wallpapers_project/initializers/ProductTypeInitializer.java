package com.odintsov.wallpapers_project.initializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odintsov.wallpapers_project.domain.entities.ProductType;
import com.odintsov.wallpapers_project.domain.repositories.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductTypeInitializer {

    private final ProductTypeRepository productTypeRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void initTypes() throws IOException {
        if (productTypeRepository.count() > 0) {
            return;
        }

        List<ProductType> types = objectMapper.readValue(
                new ClassPathResource("data/product_types.json").getInputStream(),
                new TypeReference<>() {
                }
        );

        productTypeRepository.saveAll(types);
    }
}
