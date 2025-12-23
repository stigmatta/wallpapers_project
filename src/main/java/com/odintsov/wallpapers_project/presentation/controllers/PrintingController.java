package com.odintsov.wallpapers_project.presentation.controllers;


import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingFilter;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingListResponse;
import com.odintsov.wallpapers_project.application.services.PrintingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/printings")
public class PrintingController {

    private final PrintingService printingService;

    public PrintingController(PrintingService printingService) {

        this.printingService = printingService;
    }

    @GetMapping
    public Page<PrintingListResponse> getSouvenirs(
            @ModelAttribute PrintingFilter filter,
            @PageableDefault(size = 20, sort = "id",
                    direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return printingService.findAll(filter, pageable);
    }

    @GetMapping("/{id:[0-9a-fA-F-]{36}}")
    public PrintingDetailedResponse getById(@PathVariable String id) {
        return printingService.findById(id);
    }

    @GetMapping("/{slug}")
    public PrintingDetailedResponse getBySlug(@PathVariable String slug) {
        return printingService.findBySlug(slug);
    }
}
