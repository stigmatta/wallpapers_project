package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperFilter;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperListResponse;
import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import com.odintsov.wallpapers_project.domain.entities.WallpaperRoom;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRepository;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class WallpaperService extends BaseCrudService<Wallpaper,
        Long, WallpaperFilter, WallpaperListResponse, WallpaperDetailedResponse, WallpaperRepository>
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
            spec = spec.and((root, query, cb) -> {
                Join<Wallpaper, Category> categories = root.join("categories");
                return cb.equal(categories.get("id"), filter.category());
            });
        }

        if (filter.basePrice() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("basePrice"), filter.basePrice()));
        }

        return spec;
    }

    @Override
    protected WallpaperListResponse toListResponseDto(Wallpaper entity) {
        return new WallpaperListResponse(
                entity.getId(),
                entity.getName(),
                entity.getImage(),
                entity.getCategories().stream()
                        .map(Category::getName)
                        .toList(),
                entity.getBasePrice(),
                entity.getSalePrice()
        );
    }

    @Override
    protected WallpaperDetailedResponse toDetailedResponseDto(Wallpaper entity) {
        return new WallpaperDetailedResponse (
                entity.getId(),
                entity.getName(),
                entity.getArticle(),
                entity.getBasePrice(),
                entity.getSalePrice(),
                entity.getImage(),
                entity.getDescription(),
                entity.getDensity(),
                entity.getWaterproof(),
                entity.getRooms().stream()
                        .map(WallpaperRoom::getName).collect(Collectors.toSet())
        );
    }


}
