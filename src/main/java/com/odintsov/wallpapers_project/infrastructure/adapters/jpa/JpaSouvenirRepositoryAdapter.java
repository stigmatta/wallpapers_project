package com.odintsov.wallpapers_project.infrastructure.adapters.jpa;

import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirFilter;
import com.odintsov.wallpapers_project.domain.entities.Souvenir;
import com.odintsov.wallpapers_project.domain.repositories.SouvenirRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.jpa.JpaSouvenirRepository;
import com.odintsov.wallpapers_project.infrastructure.utils.BaseProductSpecifications;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("jpa")
public class JpaSouvenirRepositoryAdapter
        extends BaseJpaRepositoryAdapter<Souvenir, String, SouvenirFilter, JpaSouvenirRepository>
        implements SouvenirRepository {

    public JpaSouvenirRepositoryAdapter(JpaSouvenirRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public Page<Souvenir> filter(SouvenirFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, BaseProductSpecifications::buildSpecification);
    }

    @Override
    public Optional<Souvenir> findBySlug(String slug) {
        return jpaRepository.findBySlug(slug);
    }
}