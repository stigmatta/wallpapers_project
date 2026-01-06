package com.odintsov.wallpapers_project.application.mappers;

import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperListResponse;
import com.odintsov.wallpapers_project.application.mappers.common.DtoMapper;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import com.odintsov.wallpapers_project.domain.entities.WallpaperRoom;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class WallpaperMapper implements DtoMapper<
        Wallpaper,
        WallpaperListResponse,
        WallpaperDetailedResponse
        > {

    private final CategoryMapper categoryMapper;

    public WallpaperMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public WallpaperListResponse toListResponseDto(Wallpaper entity) {
        return WallpaperListResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .article(entity.getArticle())
                .categories(
                        entity.getCategories() == null
                                ? Collections.emptyList()
                                : entity.getCategories().stream().map(categoryMapper::toResponse).collect(Collectors.toList())
                )
                .basePrice(entity.getPrice())
                .salePrice(entity.getSalePrice())
                .slug(entity.getSlug())
                .build();
    }

    @Override
    public WallpaperDetailedResponse toDetailedResponseDto(Wallpaper entity) {
        return WallpaperDetailedResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .article(entity.getArticle())
                .basePrice(entity.getPrice())
                .salePrice(entity.getSalePrice())
                .image(entity.getImage())
                .description(entity.getDescription())
                .density(entity.getDensity())
                .waterproof(entity.getWaterproof())
                .rooms(entity.getRooms().stream()
                        .map(WallpaperRoom::getName)
                        .collect(Collectors.toSet()))
                .materials(new HashSet<>(entity.getMaterials()))
                .categories(
                        entity.getCategories() == null
                                ? Collections.emptyList()
                                : entity.getCategories().stream().map(categoryMapper::toResponse).collect(Collectors.toList())
                )
                .build();
    }

}

