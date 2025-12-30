package com.odintsov.wallpapers_project.infrastructure.filterBuilders;

import com.google.cloud.firestore.Query;
import com.odintsov.wallpapers_project.infrastructure.interfaces.FirestoreFilterBuilder;
import javax.lang.model.type.NullType;

/**
 * A "No-Op" filter builder used for entities that do not support or require filtering.
 * <p>
 * This follows the Null Object Pattern, ensuring that the repository's internal
 * filter logic can always call {@code apply()} without checking for null builders.
 * </p>
 */
public class NullTypeFirestoreFilterBuilder implements FirestoreFilterBuilder<NullType> {

    /**
     * Returns the provided query as-is, without adding any {@code where} or {@code orderBy} clauses.
     * * @param baseQuery the starting Firestore query.
     * @param filter the null filter (ignored).
     * @return the same baseQuery instance.
     */
    @Override
    public Query apply(Query baseQuery, NullType filter) {
        return baseQuery;
    }
}