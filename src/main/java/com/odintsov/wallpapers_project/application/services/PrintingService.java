package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingFilter;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingListResponse;
import com.odintsov.wallpapers_project.application.mappers.PrintingMapper;
import com.odintsov.wallpapers_project.application.utils.BaseProductSpecifications;
import com.odintsov.wallpapers_project.domain.entities.Printing;
import com.odintsov.wallpapers_project.domain.repositories.PrintingRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PrintingService extends BaseCrudService<
        Printing,
        Long,
        PrintingFilter,
        PrintingListResponse,
        PrintingDetailedResponse,
        PrintingRepository
        > {

    public PrintingService(PrintingRepository repository,
                           PrintingMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected Specification<Printing> buildSpecification(PrintingFilter filter) {

        return BaseProductSpecifications.buildSpecification(filter);
    }
}

