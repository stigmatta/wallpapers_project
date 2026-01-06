package com.odintsov.wallpapers_project.application.mappers;

import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirListResponse;
import com.odintsov.wallpapers_project.application.mappers.common.DtoMapper;
import com.odintsov.wallpapers_project.domain.entities.Souvenir;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class SouvenirMapper implements DtoMapper<
        Souvenir,
        SouvenirListResponse,
        SouvenirDetailedResponse
        > {

    private final CatalogMapper catalogMapper;

    public SouvenirMapper(CatalogMapper catalogMapper) {
        this.catalogMapper = catalogMapper;
    }


    @Override
    public SouvenirListResponse toListResponseDto(Souvenir entity) {
        return SouvenirListResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .article(entity.getArticle())
                .categories(
                        entity.getCategories() == null
                                ? Collections.emptyList()
                                : entity.getCategories().stream().map(catalogMapper::categoryToResponse).collect(Collectors.toList())
                )
                .basePrice(entity.getPrice())
                .salePrice(entity.getSalePrice())
                .slug(entity.getSlug())
                .build();
    }

    @Override
    public SouvenirDetailedResponse toDetailedResponseDto(Souvenir entity) {
        return SouvenirDetailedResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .article(entity.getArticle())
                .basePrice(entity.getPrice())
                .salePrice(entity.getSalePrice())
                .image(entity.getImage())
                .description(entity.getDescription())
                .length(entity.getLength())
                .width(entity.getWidth())
                .thickness(entity.getThickness())
                .categories(
                        entity.getCategories() == null
                                ? Collections.emptyList()
                                : entity.getCategories().stream().map(catalogMapper::categoryToResponse).collect(Collectors.toList())
                )
                .build();
    }

}
