package com.odintsov.wallpapers_project.infrastructure.adapters;

import com.odintsov.wallpapers_project.domain.entities.Printing;
import com.odintsov.wallpapers_project.domain.repositories.PrintingRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.JpaPrintingRepository;
import org.springframework.stereotype.Component;

@Component
public class PrintingRepositoryAdapter
        extends BaseJpaRepositoryAdapter<Printing, Long, JpaPrintingRepository>
        implements PrintingRepository {

    public PrintingRepositoryAdapter(JpaPrintingRepository jpaRepository) {
        super(jpaRepository);
    }
}