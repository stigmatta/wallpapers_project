package com.odintsov.wallpapers_project.infrastructure.interfaces;

import com.google.cloud.firestore.Query;

public interface FirestoreFilterBuilder<F> {

    Query apply(Query baseQuery, F filter);
}
