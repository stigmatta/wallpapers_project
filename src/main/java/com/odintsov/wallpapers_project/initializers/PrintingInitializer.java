package com.odintsov.wallpapers_project.initializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odintsov.wallpapers_project.domain.entities.PrintMethod;
import com.odintsov.wallpapers_project.domain.entities.Printing;
import com.odintsov.wallpapers_project.domain.entities.PrintingMethodsLink;
import com.odintsov.wallpapers_project.domain.entities.ProductType;
import com.odintsov.wallpapers_project.domain.enums.ProductTypes;
import com.odintsov.wallpapers_project.domain.repositories.*;
import com.odintsov.wallpapers_project.initializers.dtos.PrintingJson;
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
public class PrintingInitializer {

    private final PrintingRepository printingRepository;
    private final ProductRepository productRepository;
    private final PrintingMethodRepository methodsRepository;
    private final PrintingMethodLinkRepository linkRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void initPrintings() throws IOException {
        if (printingRepository.count() > 0) {
            return;
        }

        ProductType printingType = productTypeRepository.findByName(ProductTypes.PRINTING)
                .orElseThrow(() -> new RuntimeException("ProductType not found in DB!"));

        if (methodsRepository.count() == 0) {
            List<PrintMethod> methods = objectMapper.readValue(
                    new ClassPathResource("data/print_methods.json").getInputStream(),
                    new TypeReference<>() {
                    }
            );
            methodsRepository.saveAll(methods);
        }

        Map<String, PrintMethod> methodMap = methodsRepository.findAll().stream()
                .collect(Collectors.toMap(PrintMethod::getName, m -> m));

        List<PrintingJson> printingData = objectMapper.readValue(
                new ClassPathResource("data/printings.json").getInputStream(),
                new TypeReference<>() {
                }
        );

        for (PrintingJson data : printingData) {
            Printing printing = Printing.builder()
                    .name(data.name())
                    .productType(printingType)
                    .article(data.article())
                    .price(data.basePrice())
                    .salePrice(data.salePrice())
                    .image(data.image())
                    .description(data.description())
                    .quantity(data.quantity())
                    .build();

            Printing savedPrinting = productRepository.save(printing);

            List<PrintingMethodsLink> links = data.methods().stream()
                    .map(methodMap::get)
                    .filter(Objects::nonNull)
                    .map(method -> PrintingMethodsLink.builder()
                            .printing(savedPrinting)
                            .method(method)
                            .build())
                    .toList();

            linkRepository.saveAll(links);
        }

        printingRepository.flush();
    }
}
