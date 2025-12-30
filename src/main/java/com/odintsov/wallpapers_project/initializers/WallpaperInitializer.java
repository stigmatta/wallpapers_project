package com.odintsov.wallpapers_project.initializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import com.odintsov.wallpapers_project.domain.entities.WallpaperMaterial;
import com.odintsov.wallpapers_project.domain.entities.WallpaperRoom;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperMaterialRepository;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRepository;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRoomRepository;
import com.odintsov.wallpapers_project.initializers.dtos.WallpaperJson;
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
    private final WallpaperMaterialRepository materialRepository;
    private final WallpaperRoomRepository roomRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void initWallpapers() throws IOException {
        if (wallpaperRepository.count() > 0) {
            return;
        }

        if (materialRepository.count() == 0) {
            List<WallpaperMaterial> materials = objectMapper.readValue(
                    new ClassPathResource("data/wallpaper_materials.json").getInputStream(),
                    new TypeReference<>() {
                    }
            );
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

        List<WallpaperJson> wallpaperData = objectMapper.readValue(
                new ClassPathResource("data/wallpapers.json").getInputStream(),
                new TypeReference<>() {
                }
        );

        List<Wallpaper> wallpapers = wallpaperData.stream().map(data ->
                Wallpaper.builder()
                        .name(data.name())
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
                        .build()
        ).collect(Collectors.toList());

        wallpaperRepository.saveAll(wallpapers);
        wallpaperRepository.flush();
    }
}