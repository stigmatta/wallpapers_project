package com.odintsov.wallpapers_project.presentation.controllers;

import com.odintsov.wallpapers_project.application.dtos.CategoryResponse;
import com.odintsov.wallpapers_project.application.dtos.ExtraFeatureResponse;
import com.odintsov.wallpapers_project.application.dtos.ProductTypeResponse;
import com.odintsov.wallpapers_project.application.services.CatalogService;
import com.odintsov.wallpapers_project.application.usecases.GlobalSearch.GlobalSearchResponse;
import com.odintsov.wallpapers_project.application.usecases.GlobalSearch.GlobalSearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final GlobalSearchUseCase globalSearchUseCase;
    private final CatalogService catalogService;


    @GetMapping("/search")
    public GlobalSearchResponse search(@RequestParam String query) {
        return globalSearchUseCase.execute(query);
    }

    @GetMapping("/categories")
    public List<CategoryResponse> getCategories() {
        return catalogService.getCategories();
    }

    @GetMapping("/features/{productTypeId}")
    public List<ExtraFeatureResponse> getFeatures(@PathVariable String productTypeId) {
        return catalogService.getFeatures(productTypeId);
    }

    @GetMapping("/product-types")
    public List<ProductTypeResponse> getProductTypes() {
        return catalogService.getProductTypes();
    }
}
