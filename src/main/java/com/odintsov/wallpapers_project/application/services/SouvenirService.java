package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirFilter;
import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirListResponse;
import com.odintsov.wallpapers_project.application.mappers.SouvenirMapper;
import com.odintsov.wallpapers_project.application.utils.BaseProductSpecifications;
import com.odintsov.wallpapers_project.domain.entities.Souvenir;
import com.odintsov.wallpapers_project.domain.repositories.SouvenirRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SouvenirService extends BaseCrudService<
        Souvenir,
        Long,
        SouvenirFilter,
        SouvenirListResponse,
        SouvenirDetailedResponse,
        SouvenirRepository
        > {

    public SouvenirService(SouvenirRepository repository,
                           SouvenirMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected Specification<Souvenir> buildSpecification(SouvenirFilter filter) {

        Specification<Souvenir> spec = BaseProductSpecifications.buildSpecification(filter);

        if (filter.getWidth() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("width"), filter.getWidth()));
        }

        if (filter.getLength() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("length"), filter.getLength()));
        }

        if (filter.getThickness() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("thickness"), filter.getThickness()));
        }
        return spec;
    }
}

