package com.odintsov.wallpapers_project.application.mappers;

import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperListResponse;
import com.odintsov.wallpapers_project.application.mappers.common.DtoMapper;
import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import com.odintsov.wallpapers_project.domain.entities.WallpaperRoom;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class WallpaperMapper implements DtoMapper<
        Wallpaper,
        WallpaperListResponse,
        WallpaperDetailedResponse
        > {

    @Override
    public WallpaperListResponse toListResponseDto(Wallpaper entity) {
        return WallpaperListResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .article(entity.getArticle())
                .categoryNames(entity.getCategories().stream()
                        .map(Category::getName)
                        .toList())
                .basePrice(entity.getBasePrice())
                .salePrice(entity.getSalePrice())
                .build();
    }

    @Override
    public WallpaperDetailedResponse toDetailedResponseDto(Wallpaper entity) {
        return WallpaperDetailedResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .article(entity.getArticle())
                .basePrice(entity.getBasePrice())
                .salePrice(entity.getSalePrice())
                .image(entity.getImage())
                .description(entity.getDescription())
                .density(entity.getDensity())
                .waterproof(entity.getWaterproof())
                .rooms(entity.getRooms().stream()
                        .map(WallpaperRoom::getName)
                        .collect(Collectors.toSet()))
                .build();
    }

}

