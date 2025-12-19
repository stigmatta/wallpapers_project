package com.odintsov.wallpapers_project.infrastructure.adapters;

import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingFilter;
import com.odintsov.wallpapers_project.infrastructure.utils.BaseProductSpecifications;
import com.odintsov.wallpapers_project.domain.entities.Printing;
import com.odintsov.wallpapers_project.domain.repositories.PrintingRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.JpaPrintingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PrintingRepositoryAdapter
        extends BaseJpaRepositoryAdapter<Printing, String, PrintingFilter, JpaPrintingRepository>
        implements PrintingRepository {

    public PrintingRepositoryAdapter(JpaPrintingRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public Page<Printing> filter(PrintingFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, BaseProductSpecifications::buildSpecification);
    }
}