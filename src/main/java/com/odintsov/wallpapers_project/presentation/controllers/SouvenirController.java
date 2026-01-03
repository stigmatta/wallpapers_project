package com.odintsov.wallpapers_project.presentation.controllers;


import com.odintsov.wallpapers_project.application.dtos.CategoryResponse;
import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirFilter;
import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirListResponse;
import com.odintsov.wallpapers_project.application.services.SouvenirService;
import com.odintsov.wallpapers_project.presentation.dtos.SouvenirCatalogResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST Controller providing endpoints for souvenir product management.
 * <p>
 * This controller handles public requests to browse the souvenir catalog,
 * providing support for dynamic filtering via {@link SouvenirFilter} and
 * pagination. It supports resource retrieval by both unique UUID and
 * SEO-friendly slugs.
 */
@RestController
@RequestMapping("/souvenirs")
public class SouvenirController {

    private final SouvenirService souvenirService;

    public SouvenirController(SouvenirService souvenirService) {

        this.souvenirService = souvenirService;
    }

    @GetMapping
    public ResponseEntity<SouvenirCatalogResponse> getSouvenirs(
            @ModelAttribute SouvenirFilter filter,
            @PageableDefault(size = 20, sort = "id",
                    direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<SouvenirListResponse> souvenirsPage = souvenirService.findAll(filter, pageable);

        List<CategoryResponse> categories = souvenirService.getAvailableCategories();

        return ResponseEntity.ok(
                SouvenirCatalogResponse.builder()
                        .products(souvenirsPage)
                        .availableCategories(categories)
                        .build()
        );
    }

    @GetMapping("/{id:[0-9a-fA-F-]{36}}")
    public SouvenirDetailedResponse getById(@PathVariable String id) {
        return souvenirService.findById(id);
    }

    @GetMapping("/{slug}")
    public SouvenirDetailedResponse getBySlug(@PathVariable String slug) {
        return souvenirService.findBySlug(slug);
    }
}
