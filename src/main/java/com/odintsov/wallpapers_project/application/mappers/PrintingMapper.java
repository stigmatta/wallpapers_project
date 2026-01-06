package com.odintsov.wallpapers_project.application.mappers;

import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingListResponse;
import com.odintsov.wallpapers_project.application.mappers.common.DtoMapper;
import com.odintsov.wallpapers_project.domain.entities.Printing;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;


@Service
public class PrintingMapper implements DtoMapper<
        Printing,
        PrintingListResponse,
        PrintingDetailedResponse
        > {

    private final CategoryMapper categoryMapper;

    public PrintingMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public PrintingListResponse toListResponseDto(Printing entity) {
        return PrintingListResponse.builder()
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
    public PrintingDetailedResponse toDetailedResponseDto(Printing entity) {
        return PrintingDetailedResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .article(entity.getArticle())
                .basePrice(entity.getPrice())
                .salePrice(entity.getSalePrice())
                .image(entity.getImage())
                .description(entity.getDescription())
                .methods(entity.getMethods())
                .build();
    }

}

