package com.odintsov.wallpapers_project.application.mappers;

import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingListResponse;
import com.odintsov.wallpapers_project.application.mappers.common.DtoMapper;
import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.entities.Printing;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class PrintingMapper implements DtoMapper<
        Printing,
        PrintingListResponse,
        PrintingDetailedResponse
        > {

    @Override
    public PrintingListResponse toListResponseDto(Printing entity) {
        return PrintingListResponse.builder()
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
                .basePrice(entity.getPrice())
                .salePrice(entity.getSalePrice())
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
                .build();
    }

}

