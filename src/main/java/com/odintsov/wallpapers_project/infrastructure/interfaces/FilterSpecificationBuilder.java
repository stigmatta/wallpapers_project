package com.odintsov.wallpapers_project.infrastructure.interfaces;

import org.springframework.data.jpa.domain.Specification;

public interface FilterSpecificationBuilder<T, FilterDTO> {
    Specification<T> build(FilterDTO filter);
}
