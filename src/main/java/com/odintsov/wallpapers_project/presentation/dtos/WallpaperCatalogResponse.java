package com.odintsov.wallpapers_project.presentation.dtos;

import com.odintsov.wallpapers_project.application.dtos.CategoryResponse;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperListResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
public class WallpaperCatalogResponse {
    private Page<WallpaperListResponse> products;
    private List<CategoryResponse> availableCategories;
}
