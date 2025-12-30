package com.odintsov.wallpapers_project.infrastructure.adapters.jpa;

import com.odintsov.wallpapers_project.domain.entities.PrintingMethodsLink;
import com.odintsov.wallpapers_project.domain.repositories.PrintingMethodLinkRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.jpa.JpaPrintingMethodLinkRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("jpa")
public class JpaPrintingMethodLinkRepositoryAdapter implements PrintingMethodLinkRepository {

    private final JpaPrintingMethodLinkRepository jpaRepository;

    public JpaPrintingMethodLinkRepositoryAdapter(JpaPrintingMethodLinkRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<PrintingMethodsLink> saveAll(List<PrintingMethodsLink> entities) {
        return jpaRepository.saveAll(entities);
    }
}
