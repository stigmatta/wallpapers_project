package com.odintsov.wallpapers_project.infrastructure.filterBuilders;

import com.google.cloud.firestore.Query;
import com.odintsov.wallpapers_project.infrastructure.interfaces.FirestoreFilterBuilder;
import com.odintsov.wallpapers_project.application.dtos.common.BaseProduct.BaseProductFilter;

public class BaseProductFirestoreFilterBuilder<F extends BaseProductFilter>
        implements FirestoreFilterBuilder<F> {

    @Override
    public Query apply(Query baseQuery, F filter) {
        if (filter == null) return baseQuery;

        Query query = baseQuery;

        // Фильтр по имени
        if (filter.getName() != null && !filter.getName().isBlank()) {
            String name = filter.getName().toLowerCase();
            query = query
                    .orderBy("nameLower")
                    .startAt(name)
                    .endAt(name + "\uf8ff");
        }

        // Фильтр по категории
        if (filter.getCategoryId() != null) {
            query = query.whereArrayContains("categoryIds", filter.getCategoryId());
        }

        // Фильтр по базовой цене
        if (filter.getBasePrice() != null) {
            query = query.whereLessThanOrEqualTo("basePrice", filter.getBasePrice());
        }

        return query;
    }
}
