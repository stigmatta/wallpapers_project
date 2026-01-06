package com.odintsov.wallpapers_project.infrastructure.filterBuilders;

import com.google.cloud.firestore.Query;
import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirFilter;

public class SouvenirFilterBuilder extends BaseProductFirestoreFilterBuilder<SouvenirFilter> {

    @Override
    public Query apply(Query baseQuery, SouvenirFilter filter) {
        return super.apply(baseQuery, filter);
    }
}
