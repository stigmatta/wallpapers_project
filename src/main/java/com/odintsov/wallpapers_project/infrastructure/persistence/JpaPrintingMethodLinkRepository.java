package com.odintsov.wallpapers_project.infrastructure.persistence;

import com.odintsov.wallpapers_project.domain.entities.PrintingMethodsLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaPrintingMethodLinkRepository
        extends JpaRepository<PrintingMethodsLink, Long>, JpaSpecificationExecutor<PrintingMethodsLink> {
}
