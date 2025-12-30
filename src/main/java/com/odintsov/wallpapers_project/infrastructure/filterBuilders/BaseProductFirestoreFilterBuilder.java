package com.odintsov.wallpapers_project.infrastructure.filterBuilders;

import com.google.cloud.firestore.Query;
import com.odintsov.wallpapers_project.application.dtos.common.BaseProduct.BaseProductFilter;
import com.odintsov.wallpapers_project.domain.enums.ProductFields;
import com.odintsov.wallpapers_project.infrastructure.interfaces.FirestoreFilterBuilder;


/**
 * A reusable builder for constructing Firestore queries based on {@link BaseProductFilter}.
 * <p>
 * This class handles common product filtering logic, such as prefix-based name searches,
 * category membership checks via array intersections, and price range constraints.
 * </p>
 *
 * @param <F> A subtype of BaseProductFilter to allow extension for specific products.
 */
public class BaseProductFirestoreFilterBuilder<F extends BaseProductFilter>
        implements FirestoreFilterBuilder<F> {

    @Override
    public Query apply(Query baseQuery, F filter) {
        if (filter == null) return baseQuery;

        Query query = baseQuery;

        if (filter.getName() != null && !filter.getName().isBlank()) {
            String name = filter.getName();
            query = query
                    .orderBy("name")
                    .startAt(name)
                    .endAt(name + "\uf8ff");
        }

        if (filter.getCategoryId() != null) {
            query = query.whereArrayContains("categoryIds", filter.getCategoryId());
        }

        if (filter.getBasePrice() != null) {
            query = query.whereLessThanOrEqualTo(ProductFields.PRICE, filter.getBasePrice());
        }

        return query;
    }
}
