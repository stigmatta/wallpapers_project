package com.odintsov.wallpapers_project.presentation.controllers;


import com.odintsov.wallpapers_project.application.dtos.CategoryResponse;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintMethodResponse;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingFilter;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingListResponse;
import com.odintsov.wallpapers_project.application.services.PrintingService;
import com.odintsov.wallpapers_project.presentation.dtos.PrintingCatalogResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST Controller for managing printing products and souvenirs.
 * <p>
 * This controller provides endpoints for public access to the printing catalog,
 * supporting advanced filtering, pagination, and dual-lookup strategies (UUID or Slug).
 */
@RestController
@RequestMapping("/printings")
public class PrintingController {

    private final PrintingService printingService;

    public PrintingController(PrintingService printingService) {

        this.printingService = printingService;
    }

    @GetMapping
    public ResponseEntity<PrintingCatalogResponse> getSouvenirs(
            @ModelAttribute PrintingFilter filter,
            @PageableDefault(size = 20, sort = "id",
                    direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<PrintingListResponse> printingPage = printingService.findAll(filter, pageable);

        List<CategoryResponse> categories = printingService.getAvailableCategories();

        return ResponseEntity.ok(
                PrintingCatalogResponse.builder()
                        .products(printingPage)
                        .availableCategories(categories)
                        .build()
        );
    }

    @GetMapping("/{id:[0-9a-fA-F-]{36}}")
    public PrintingDetailedResponse getById(@PathVariable String id) {
        return printingService.findById(id);
    }

    @GetMapping("/{slug}")
    public PrintingDetailedResponse getBySlug(@PathVariable String slug) {
        return printingService.findBySlug(slug);
    }

    @GetMapping("/methods")
    public List<PrintMethodResponse> getMethods() {
        return printingService.getPrintMethods();
    }
}
