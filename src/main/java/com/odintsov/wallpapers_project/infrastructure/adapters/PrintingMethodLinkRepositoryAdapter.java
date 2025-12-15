package com.odintsov.wallpapers_project.infrastructure.adapters;

import com.odintsov.wallpapers_project.domain.entities.PrintingMethodsLink;
import com.odintsov.wallpapers_project.domain.repositories.PrintingMethodLinkRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.JpaPrintingMethodLinkRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrintingMethodLinkRepositoryAdapter implements PrintingMethodLinkRepository {

    private final JpaPrintingMethodLinkRepository jpaRepository;

    public PrintingMethodLinkRepositoryAdapter(JpaPrintingMethodLinkRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<PrintingMethodsLink> saveAll(List<PrintingMethodsLink> entities) {
        return jpaRepository.saveAll(entities);
    }
}
