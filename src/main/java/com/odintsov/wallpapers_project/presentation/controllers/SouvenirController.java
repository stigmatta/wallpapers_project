package com.odintsov.wallpapers_project.presentation.controllers;


import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirFilter;
import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirListResponse;
import com.odintsov.wallpapers_project.application.services.SouvenirService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


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
    public Page<SouvenirListResponse> getSouvenirs(
            @ModelAttribute SouvenirFilter filter,
            @PageableDefault(size = 20, sort = "id",
                    direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return souvenirService.findAll(filter, pageable);
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
