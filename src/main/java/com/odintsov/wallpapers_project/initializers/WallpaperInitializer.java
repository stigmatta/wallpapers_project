package com.odintsov.wallpapers_project.initializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odintsov.wallpapers_project.domain.entities.*;
import com.odintsov.wallpapers_project.domain.enums.ProductTypes;
import com.odintsov.wallpapers_project.domain.repositories.*;
import com.odintsov.wallpapers_project.infrastructure.utils.ProductTypeRegistry;
import com.odintsov.wallpapers_project.initializers.dtos.WallpaperJson;
import com.odintsov.wallpapers_project.initializers.dtos.WallpaperMaterialJson;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class WallpaperInitializer {

    private final WallpaperRepository wallpaperRepository;
    private final ProductRepository productRepository;
    private final WallpaperMaterialRepository materialRepository;
    private final ExtraFeatureRepository extraFeatureRepository;
    private final ProductTypeRepository productTypeRepository;
    private final CategoryRepository categoryRepository;
    private final WallpaperRoomRepository roomRepository;
    private final ObjectMapper objectMapper;
    private final ProductTypeRegistry productTypeRegistry;


    @Transactional
    public void initWallpapers() throws IOException {
        if (wallpaperRepository.count() > 0) {
            return;
        }

        ProductType wallpaperType = productTypeRepository.findByName(ProductTypes.WALLPAPER)
                .orElseThrow(() -> new RuntimeException("ProductType not found in DB!"));

        if (materialRepository.count() == 0) {
            List<WallpaperMaterialJson> materialsData = objectMapper.readValue(
                    new ClassPathResource("data/wallpaper_materials.json").getInputStream(),
                    new TypeReference<>() {
                    }
            );

            List<WallpaperMaterial> materials = materialsData.stream().map(data ->
                    WallpaperMaterial.builder()
                            .name(data.name())
                            .description(data.description())
                            .priceMultiplier(data.priceMultiplier())
                            .image(data.image())
                            .build()
            ).collect(Collectors.toList());

            materialRepository.saveAll(materials);
        }

        Map<String, WallpaperMaterial> materialMap = materialRepository.findAll().stream()
                .collect(Collectors.toMap(WallpaperMaterial::getName, m -> m));

        if (roomRepository.count() == 0) {
            List<WallpaperRoom> rooms = objectMapper.readValue(
                    new ClassPathResource("data/wallpaper_rooms.json").getInputStream(),
                    new TypeReference<>() {
                    }
            );
            rooms.forEach(r -> {
                if (r.getId() == null) r.setId(UUID.randomUUID().toString());
            });
            roomRepository.saveAll(rooms);
        }
        Map<String, WallpaperRoom> roomMap = roomRepository.findAll().stream()
                .collect(Collectors.toMap(WallpaperRoom::getName, r -> r));

        Map<String, Category> categoryMap = categoryRepository.findByProductTypeId(wallpaperType.getId())
                .stream()
                .collect(Collectors.toMap(Category::getName, c -> c));

        List<WallpaperJson> wallpaperData = objectMapper.readValue(
                new ClassPathResource("data/wallpapers.json").getInputStream(),
                new TypeReference<>() {
                }
        );

        List<Wallpaper> wallpapers = wallpaperData.stream().map(data -> {
            List<Category> wallpaperCategories = data.categories().stream()
                    .map(categoryMap::get)
                    .filter(Objects::nonNull)
                    .toList();

            return Wallpaper.builder()
                    .name(data.name())
                    .slug(data.slug())
                    .productType(wallpaperType)
                    .article(data.article())
                    .price(data.basePrice())
                    .salePrice(data.salePrice())
                    .image(data.image())
                    .description(data.description())
                    .density(data.density())
                    .waterproof(data.waterproof())
                    .quantity(data.quantity())
                    .materials(new ArrayList<>(data.materials().stream()
                            .map(materialMap::get)
                            .filter(Objects::nonNull)
                            .toList()))
                    .rooms(new ArrayList<>(data.rooms().stream()
                            .map(roomMap::get)
                            .filter(Objects::nonNull)
                            .toList()))
                    .categories(new ArrayList<>(wallpaperCategories))
                    .build();
        }).collect(Collectors.toList());

        productRepository.saveAll(wallpapers);
        wallpaperRepository.flush();
    }
}