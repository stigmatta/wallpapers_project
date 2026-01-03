package com.odintsov.wallpapers_project.presentation.dtos;

import com.odintsov.wallpapers_project.application.dtos.CategoryResponse;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingListResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
public class PrintingCatalogResponse {
    private Page<PrintingListResponse> products;
    private List<CategoryResponse> availableCategories;
}
