package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingFilter;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingListResponse;
import com.odintsov.wallpapers_project.application.mappers.PrintingMapper;
import com.odintsov.wallpapers_project.domain.entities.Printing;
import com.odintsov.wallpapers_project.domain.repositories.PrintingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrintingService extends BaseCrudService<
        Printing,
        String,
        PrintingFilter,
        PrintingListResponse,
        PrintingDetailedResponse,
        PrintingRepository
        > {

    public PrintingService(PrintingRepository repository,
                           PrintingMapper mapper) {
        super(repository, mapper);
    }

    public PrintingDetailedResponse findBySlug(String slug) {
        Printing printing = repository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("printing not found with slug: " + slug));

        return mapper.toDetailedResponseDto(printing);
    }
}

