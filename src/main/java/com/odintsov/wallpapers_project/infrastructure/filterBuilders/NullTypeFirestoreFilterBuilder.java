package com.odintsov.wallpapers_project.infrastructure.filterBuilders;

import com.google.cloud.firestore.Query;
import com.odintsov.wallpapers_project.infrastructure.interfaces.FirestoreFilterBuilder;

import javax.lang.model.type.NullType;

public class NullTypeFirestoreFilterBuilder implements FirestoreFilterBuilder<NullType> {
    @Override
    public Query apply(Query baseQuery, NullType filter) {
        return baseQuery;
    }
}

