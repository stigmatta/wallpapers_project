package com.odintsov.wallpapers_project.presentation.controllers;

import com.odintsov.wallpapers_project.application.dtos.CategoryResponse;
import com.odintsov.wallpapers_project.application.mappers.CategoryMapper;
import com.odintsov.wallpapers_project.application.usecases.GlobalSearch.GlobalSearchResponse;
import com.odintsov.wallpapers_project.application.usecases.GlobalSearch.GlobalSearchUseCase;
import com.odintsov.wallpapers_project.domain.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final GlobalSearchUseCase globalSearchUseCase;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @GetMapping("/search")
    public GlobalSearchResponse search(@RequestParam String query) {
        return globalSearchUseCase.execute(query);
    }

    @GetMapping("/categories")
    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponse)
                .toList();
    }
}
