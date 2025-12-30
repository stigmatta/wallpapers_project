package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.odintsov.wallpapers_project.domain.entities.UserSession;
import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.SessionFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.repositories.SessionRepository;
import com.odintsov.wallpapers_project.infrastructure.mappers.FirebaseSessionMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;



/**
 * Firestore adapter for {@link UserSession} persistence.
 * <p>
 * This implementation uses the Google Cloud Firestore SDK to manage session data.
 * It performs manual mapping of domain entities to Firestore maps to ensure
 * specific formatting for date fields (ISO_LOCAL_DATE_TIME).
 */
@Component
@Primary
public class FirebaseSessionRepositoryAdapter implements SessionRepository {

    private final Firestore firestore;
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;


    public FirebaseSessionRepositoryAdapter(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public void save(UserSession session) {
        try {
            firestore.collection(TableNames.USER_SESSIONS)
                    .document(session.getToken())
                    .set(
                            Map.of(
                                    IdFields.USER_ID, session.getUserId().toString(),
                                    CommonFields.CREATED_AT, session.getCreatedAt().format(formatter),
                                    SessionFields.EXPIRES_AT, session.getExpiresAt().format(formatter)
                            )
                    ).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<UserSession> findByToken(String token) {
        try {
            DocumentSnapshot doc = firestore.collection(TableNames.USER_SESSIONS)
                    .document(token).get().get();

            if (!doc.exists()) return Optional.empty();

            return Optional.of(FirebaseSessionMapper.toEntity(doc));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }


    @Override
    public List<UserSession> findAllByUserId(UUID userId) {
        try {
            return firestore.collection(TableNames.USER_SESSIONS)
                    .whereEqualTo(IdFields.USER_ID, userId.toString())
                    .get().get()
                    .getDocuments()
                    .stream()
                    .map(FirebaseSessionMapper::toEntity)
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Error fetching sessions for user: " + userId, e);
        }
    }


    @Override
    public void deleteByToken(String token) {
        try {
            firestore.collection(TableNames.USER_SESSIONS).document(token).delete().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}


