package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.dtos.Printing.PrintMethodResponse;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingFilter;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingListResponse;
import com.odintsov.wallpapers_project.application.mappers.PrintingMapper;
import com.odintsov.wallpapers_project.domain.entities.Printing;
import com.odintsov.wallpapers_project.domain.enums.ProductTypes;
import com.odintsov.wallpapers_project.domain.repositories.PrintingMethodRepository;
import com.odintsov.wallpapers_project.domain.repositories.PrintingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrintingService extends BaseProductService<
        Printing, String, PrintingFilter, PrintingListResponse, PrintingDetailedResponse, PrintingRepository
        > {

    private final PrintingMethodRepository printingMethodRepository;
    private final PrintingMapper printingMapper;

    public PrintingService(PrintingRepository repository, PrintingMapper mapper, PrintingMethodRepository printingMethodRepository) {
        super(repository, mapper, ProductTypes.PRINTING);
        this.printingMethodRepository = printingMethodRepository;
        this.printingMapper = mapper;
    }

    public List<PrintMethodResponse> getPrintMethods() {
        return printingMethodRepository.findAll().stream().map(printingMapper::toPrintMethodResponse).toList();
    }
}