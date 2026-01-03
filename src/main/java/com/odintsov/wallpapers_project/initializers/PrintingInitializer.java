package com.odintsov.wallpapers_project.initializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odintsov.wallpapers_project.application.exceptions.EntityNotFoundException;
import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.entities.PrintMethod;
import com.odintsov.wallpapers_project.domain.entities.Printing;
import com.odintsov.wallpapers_project.domain.entities.ProductType;
import com.odintsov.wallpapers_project.domain.enums.ProductTypes;
import com.odintsov.wallpapers_project.domain.repositories.*;
import com.odintsov.wallpapers_project.initializers.dtos.PrintingJson;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class PrintingInitializer {

    private final PrintingRepository printingRepository;
    private final ProductRepository productRepository;
    private final PrintingMethodRepository methodsRepository;
    private final ProductTypeRepository productTypeRepository;
    private final CategoryRepository categoryRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void initPrintings() throws IOException {
        if (printingRepository.count() > 0) {
            return;
        }

        ProductType printingType = productTypeRepository.findByName(ProductTypes.PRINTING)
                .orElseThrow(() -> new EntityNotFoundException("ProductType PRINTING not found in DB!"));

        if (methodsRepository.count() == 0) {
            List<PrintMethod> methodsData = objectMapper.readValue(
                    new ClassPathResource("data/print_methods.json").getInputStream(),
                    new TypeReference<>() {
                    }
            );

            methodsData.forEach(m -> {
                if (m.getId() == null) m.setId(UUID.randomUUID().toString());
            });
            methodsRepository.saveAll(methodsData);
        }

        Map<String, PrintMethod> methodsMap = methodsRepository.findAll().stream()
                .collect(Collectors.toMap(PrintMethod::getName, m -> m));

        Map<String, Category> categoryMap = categoryRepository.findByProductTypeId(printingType.getId())
                .stream()
                .collect(Collectors.toMap(Category::getName, c -> c));

        List<PrintingJson> printingData = objectMapper.readValue(
                new ClassPathResource("data/printings.json").getInputStream(),
                new TypeReference<>() {
                }
        );

        List<Printing> printings = printingData.stream().map(data ->
                Printing.builder()
                        .name(data.name())
                        .slug(data.slug())
                        .productType(printingType)
                        .article(data.article())
                        .price(data.basePrice())
                        .salePrice(data.salePrice())
                        .image(data.image())
                        .description(data.description())
                        .quantity(data.quantity())
                        .categories(new ArrayList<>(data.categories().stream()
                                .map(categoryMap::get)
                                .filter(Objects::nonNull)
                                .toList()))
                        .methods(new ArrayList<>(data.methods().stream()
                                .map(methodsMap::get)
                                .filter(Objects::nonNull)
                                .toList()))
                        .build()
        ).collect(Collectors.toList());

        productRepository.saveAll(printings);
        printingRepository.flush();
    }
}