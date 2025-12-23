package com.odintsov.wallpapers_project.application.mappers;

import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirListResponse;
import com.odintsov.wallpapers_project.application.mappers.common.DtoMapper;
import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.entities.Souvenir;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SouvenirMapper implements DtoMapper<
        Souvenir,
        SouvenirListResponse,
        SouvenirDetailedResponse
        > {

    @Override
    public SouvenirListResponse toListResponseDto(Souvenir entity) {
        return SouvenirListResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .article(entity.getArticle())
                .categoryNames(
                        entity.getCategories() == null
                                ? Collections.emptyList()
                                : entity.getCategories().stream()
                                .map(Category::getName)
                                .toList()
                )
                .basePrice(entity.getBasePrice())
                .salePrice(entity.getSalePrice())
                .build();
    }

    @Override
    public SouvenirDetailedResponse toDetailedResponseDto(Souvenir entity) {
        return SouvenirDetailedResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .article(entity.getArticle())
                .basePrice(entity.getBasePrice())
                .salePrice(entity.getSalePrice())
                .image(entity.getImage())
                .description(entity.getDescription())
                .length(entity.getLength())
                .width(entity.getWidth())
                .thickness(entity.getThickness())
                .build();
    }

}
