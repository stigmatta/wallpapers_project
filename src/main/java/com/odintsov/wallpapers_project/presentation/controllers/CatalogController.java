package com.odintsov.wallpapers_project.presentation.controllers;

import com.odintsov.wallpapers_project.application.usecases.GlobalSearch.GlobalSearchResponse;
import com.odintsov.wallpapers_project.application.usecases.GlobalSearch.GlobalSearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class CatalogController {

    private final GlobalSearchUseCase globalSearchUseCase;

    @GetMapping("/search")
    public GlobalSearchResponse search(@RequestParam String query) {
        return globalSearchUseCase.execute(query);
    }
}
