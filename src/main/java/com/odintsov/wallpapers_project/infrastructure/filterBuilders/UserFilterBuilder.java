package com.odintsov.wallpapers_project.infrastructure.filterBuilders;

import com.google.cloud.firestore.Query;
import com.odintsov.wallpapers_project.application.dtos.User.UserFilter;
import com.odintsov.wallpapers_project.infrastructure.interfaces.FirestoreFilterBuilder;

public class UserFilterBuilder implements FirestoreFilterBuilder<UserFilter> {

    @Override
    public Query apply(Query baseQuery, UserFilter filter) {
        if (filter == null) return baseQuery;

        Query query = baseQuery;

        if (filter.getUsername() != null && !filter.getUsername().isBlank()) {
            String username = filter.getUsername().toLowerCase();
            query = query
                    .orderBy("usernameLower")
                    .startAt(username)
                    .endAt(username + "\uf8ff");
        }

        if (filter.getEmail() != null && !filter.getEmail().isBlank()) {
            String email = filter.getEmail().toLowerCase();
            query = query
                    .orderBy("emailLower")
                    .startAt(email)
                    .endAt(email + "\uf8ff");
        }

        return query;
    }
}
