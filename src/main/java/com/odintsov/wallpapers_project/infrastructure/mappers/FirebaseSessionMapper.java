package com.odintsov.wallpapers_project.infrastructure.mappers;


import com.google.cloud.firestore.DocumentSnapshot;
import com.odintsov.wallpapers_project.domain.entities.UserSession;
import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.SessionFields;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils.parseDateTime;

public final class FirebaseSessionMapper {

    public static UserSession toEntity(DocumentSnapshot doc) {
        String token = doc.getId();
        UUID userId = UUID.fromString(Objects.requireNonNull(doc.getString(IdFields.USER_ID)));

        LocalDateTime createdAt = parseDateTime(doc.getString(CommonFields.CREATED_AT));
        LocalDateTime expiresAt = parseDateTime(doc.getString(SessionFields.EXPIRES_AT));

        return new UserSession(userId, token).withCreatedAndExpires(createdAt, expiresAt);
    }
}
