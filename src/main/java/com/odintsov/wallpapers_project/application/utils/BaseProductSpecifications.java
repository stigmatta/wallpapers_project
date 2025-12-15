package com.odintsov.wallpapers_project.application.utils;

import com.odintsov.wallpapers_project.application.dtos.common.BaseProduct.BaseProductFilter;
import com.odintsov.wallpapers_project.domain.entities.Category;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class BaseProductSpecifications {

    /**
     * Generic specification builder for any entity T that has 'name', 'categories', 'basePrice'.
     *
     * @param filter the base product filter
     * @param <T>    the entity type (e.g., Wallpaper, Souvenir)
     * @return Specification<T>
     */
    public static <T> Specification<T> buildSpecification(BaseProductFilter filter) {
        if (filter == null) {
            return Specification.unrestricted();
        }

        Specification<T> spec = Specification.unrestricted();

        if (filter.getName() != null && !filter.getName().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + filter.getName().toLowerCase() + "%"));
        }

        if (filter.getCategoryId() != null) {
            spec = spec.and((root, query, cb) -> {
                Join<T, Category> categories = root.join("categories");
                return cb.equal(categories.get("id"), filter.getCategoryId());
            });
        }

        if (filter.getBasePrice() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("basePrice"), filter.getBasePrice()));
        }

        return spec;
    }
}

