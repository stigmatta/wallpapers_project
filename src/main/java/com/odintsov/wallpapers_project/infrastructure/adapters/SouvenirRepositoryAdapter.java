package com.odintsov.wallpapers_project.infrastructure.adapters;

import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirFilter;
import com.odintsov.wallpapers_project.infrastructure.utils.BaseProductSpecifications;
import com.odintsov.wallpapers_project.domain.entities.Souvenir;
import com.odintsov.wallpapers_project.domain.repositories.SouvenirRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.JpaSouvenirRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SouvenirRepositoryAdapter
        extends BaseJpaRepositoryAdapter<Souvenir, String, SouvenirFilter, JpaSouvenirRepository>
        implements SouvenirRepository {

    public SouvenirRepositoryAdapter(JpaSouvenirRepository jpaRepository) {
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
}