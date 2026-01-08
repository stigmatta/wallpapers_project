package com.odintsov.wallpapers_project.application.mappers;

import com.odintsov.wallpapers_project.application.dtos.Printing.PrintMethodResponse;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingListResponse;
import com.odintsov.wallpapers_project.application.mappers.common.DtoMapper;
import com.odintsov.wallpapers_project.domain.entities.PrintMethod;
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

    private final CatalogMapper catalogMapper;

    public PrintingMapper(CatalogMapper catalogMapper) {
        this.catalogMapper = catalogMapper;
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
                                : entity.getCategories().stream().map(catalogMapper::categoryToResponse).collect(Collectors.toList())
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
                .categories(
                        entity.getCategories() == null
                                ? Collections.emptyList()
                                : entity.getCategories().stream().map(catalogMapper::categoryToResponse).collect(Collectors.toList())
                )
                .build();
    }

    public PrintMethodResponse toPrintMethodResponse(PrintMethod method) {
        return PrintMethodResponse.builder()
                .id(method.getId())
                .name(method.getName())
                .description(method.getDescription())
                .deadline(method.getDeadline())
                .build();
    }

}

