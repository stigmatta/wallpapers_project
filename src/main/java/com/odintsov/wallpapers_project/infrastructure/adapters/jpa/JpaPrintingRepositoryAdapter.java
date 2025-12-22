package com.odintsov.wallpapers_project.infrastructure.adapters.jpa;

import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingFilter;
import com.odintsov.wallpapers_project.infrastructure.utils.BaseProductSpecifications;
import com.odintsov.wallpapers_project.domain.entities.Printing;
import com.odintsov.wallpapers_project.domain.repositories.PrintingRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.jpa.JpaPrintingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class JpaPrintingRepositoryAdapter
        extends BaseJpaRepositoryAdapter<Printing, String, PrintingFilter, JpaPrintingRepository>
        implements PrintingRepository {

    public JpaPrintingRepositoryAdapter(JpaPrintingRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public Page<Printing> filter(PrintingFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, f -> {
            Specification<Printing> spec = BaseProductSpecifications.buildSpecification(f);

            if (f.getPrintMethod() != null && !f.getPrintMethod().isBlank()) {
                spec = spec.and((root, query, cb) ->
                        cb.equal(root.get("printMethod"), f.getPrintMethod()));
            }

            return spec;
        });
    }
}