package com.odintsov.wallpapers_project.infrastructure.filterBuilders;

import com.google.cloud.firestore.Query;
import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirFilter;
import com.odintsov.wallpapers_project.domain.enums.SouvenirFields;

public class SouvenirFilterBuilder extends BaseProductFirestoreFilterBuilder<SouvenirFilter> {

    @Override
    public Query apply(Query baseQuery, SouvenirFilter filter) {
        Query query = super.apply(baseQuery, filter);

        if (filter.getWidth() != null) {
            query = query.whereLessThanOrEqualTo(SouvenirFields.WIDTH, filter.getWidth());
        }
        if (filter.getLength() != null) {
            query = query.whereLessThanOrEqualTo(SouvenirFields.LENGTH, filter.getLength());
        }
        if (filter.getThickness() != null) {
            query = query.whereLessThanOrEqualTo(SouvenirFields.THICKNESS, filter.getThickness());
        }

        return query;
    }
}
