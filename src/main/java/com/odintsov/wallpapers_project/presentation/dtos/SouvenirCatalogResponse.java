package com.odintsov.wallpapers_project.presentation.dtos;

import com.odintsov.wallpapers_project.application.dtos.CategoryResponse;
import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirListResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
public class SouvenirCatalogResponse {
    private Page<SouvenirListResponse> products;
    private List<CategoryResponse> availableCategories;
}
