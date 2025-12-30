package com.odintsov.wallpapers_project.infrastructure.adapters.jpa;

import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirFilter;
import com.odintsov.wallpapers_project.domain.entities.Souvenir;
import com.odintsov.wallpapers_project.domain.repositories.SouvenirRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.jpa.JpaSouvenirRepository;
import com.odintsov.wallpapers_project.infrastructure.utils.BaseProductSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaSouvenirRepositoryAdapter
        extends BaseJpaRepositoryAdapter<Souvenir, String, SouvenirFilter, JpaSouvenirRepository>
        implements SouvenirRepository {

    public JpaSouvenirRepositoryAdapter(JpaSouvenirRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public Page<Souvenir> filter(SouvenirFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, f -> {
            Specification<Souvenir> spec = BaseProductSpecifications.buildSpecification(f);

            if (f.getWidth() != null) {
                spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("width"), f.getWidth()));
            }
            if (f.getLength() != null) {
                spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("length"), f.getLength()));
            }
            if (f.getThickness() != null) {
                spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("thickness"), f.getThickness()));
            }

            return spec;
        });
    }

    @Override
    public Optional<Souvenir> findBySlug(String slug) {
        return jpaRepository.findBySlug(slug);
    }
}