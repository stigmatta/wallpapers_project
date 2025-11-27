package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperFilter;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class WallpaperService extends BaseCrudService<Wallpaper,
        Long, WallpaperFilter, WallpaperRepository>
{

    protected WallpaperService(WallpaperRepository repository) {
        super(repository);
    }

    @Override
    protected Specification<Wallpaper> buildSpecification(WallpaperFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.name() != null && !filter.name().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")),
                        "%" + filter.name().toLowerCase() + "%"));
            }

            if (filter.category() != null) {
                predicates.add(cb.equal(root.get("category"), filter.category()));
            }

            if (filter.basePrice() != null) {
                predicates.add(cb.equal(root.get("basePrice"), filter.basePrice()));
            }

            if (filter.availableMaterials() != null && !filter.availableMaterials().isEmpty()) {
                predicates.add(root.get("availableMaterials").in(filter.availableMaterials()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
