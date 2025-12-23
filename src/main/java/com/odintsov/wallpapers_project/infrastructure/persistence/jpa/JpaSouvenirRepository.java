package com.odintsov.wallpapers_project.infrastructure.persistence.jpa;

import com.odintsov.wallpapers_project.domain.entities.Souvenir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface JpaSouvenirRepository extends JpaRepository<Souvenir, String>, JpaSpecificationExecutor<Souvenir> {
    Optional<Souvenir> findBySlug(String slug);
}
