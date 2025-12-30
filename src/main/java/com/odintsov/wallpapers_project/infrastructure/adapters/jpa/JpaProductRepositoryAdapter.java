package com.odintsov.wallpapers_project.infrastructure.adapters.jpa;

import com.odintsov.wallpapers_project.application.dtos.Product.ProductFilter;
import com.odintsov.wallpapers_project.domain.entities.BaseProduct;
import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.repositories.ProductRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.jpa.JpaProductRepository;
import com.odintsov.wallpapers_project.infrastructure.utils.BaseProductSpecifications;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("jpa")
public class JpaProductRepositoryAdapter
        extends BaseJpaRepositoryAdapter<BaseProduct, String, ProductFilter, JpaProductRepository>
        implements ProductRepository {

    public JpaProductRepositoryAdapter(JpaProductRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public Optional<BaseProduct> findBySlug(String slug) {
        return jpaRepository.findBySlug(slug);
    }

    @Override
    public Page<BaseProduct> filter(ProductFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, f -> {
            Specification<BaseProduct> spec = BaseProductSpecifications.buildSpecification(f);

            if (f.getProductTypeId() != null && !f.getProductTypeId().isBlank()) {
                spec = spec.and((root, query, cb) ->
                        cb.equal(root.get(CommonFields.PRODUCT_TYPE).get(CommonFields.ID), f.getProductTypeId()));
            }

            return spec;
        });
    }
}
