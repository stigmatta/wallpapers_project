package com.odintsov.wallpapers_project.infrastructure.filterBuilders;

import com.google.cloud.firestore.Query;
import com.odintsov.wallpapers_project.application.dtos.Product.ProductFilter;
import com.odintsov.wallpapers_project.domain.enums.IdFields;

public class ProductFilterBuilder extends BaseProductFirestoreFilterBuilder<ProductFilter> {

    @Override
    public Query apply(Query baseQuery, ProductFilter filter) {
        Query query = super.apply(baseQuery, filter);

        if (filter == null) return query;

        if (filter.getProductTypeId() != null && !filter.getProductTypeId().isBlank()) {
            query = query.whereEqualTo(IdFields.PRODUCT_TYPE_ID, filter.getProductTypeId());
        }

        return query;
    }
}
