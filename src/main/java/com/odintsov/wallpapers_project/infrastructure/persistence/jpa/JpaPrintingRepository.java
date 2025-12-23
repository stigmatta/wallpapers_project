package com.odintsov.wallpapers_project.infrastructure.persistence.jpa;

import com.odintsov.wallpapers_project.domain.entities.Printing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface JpaPrintingRepository extends JpaRepository<Printing, String>, JpaSpecificationExecutor<Printing> {
    Optional<Printing> findBySlug(String slug);
}
