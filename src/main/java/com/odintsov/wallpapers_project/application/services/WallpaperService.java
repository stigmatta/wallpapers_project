package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperFilter;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;



@Service
public class WallpaperService extends BaseCrudService<Wallpaper,
        Long, WallpaperFilter, WallpaperRepository>
{

    protected WallpaperService(WallpaperRepository repository) {
        super(repository);
    }

    @Override
    protected Specification<Wallpaper> buildSpecification(WallpaperFilter filter) {
        if (filter == null) {
            return Specification.unrestricted();
        }

        Specification<Wallpaper> spec = Specification.unrestricted();

        if (filter.name() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + filter.name().toLowerCase() + "%"));
        }

        if (filter.category() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("category").get("id"), filter.category()));
        }

        if (filter.basePrice() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("basePrice"), filter.basePrice()));
        }

        if (filter.availableMaterials() != null && !filter.availableMaterials().isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    root.join("availableMaterials").get("id").in(filter.availableMaterials()));
        }

        return spec;
    }


}
