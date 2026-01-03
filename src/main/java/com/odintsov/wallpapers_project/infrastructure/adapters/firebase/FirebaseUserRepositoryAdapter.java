package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.odintsov.wallpapers_project.application.dtos.User.UserFilter;
import com.odintsov.wallpapers_project.domain.entities.User;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.enums.UserFields;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import com.odintsov.wallpapers_project.infrastructure.filterBuilders.UserFilterBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils.await;


/**
 * Firestore-specific implementation of the {@link UserRepository}.
 * <p>
 * This adapter manages the {@link User} collection in Firestore, providing
 * optimized lookups for unique identifiers such as email, username, and
 * phone number. It leverages the {@link UserFilterBuilder} for advanced
 * administrative searches.
 */
@Component
@Profile("firebase")
public class FirebaseUserRepositoryAdapter
        extends BaseFirebaseRepositoryAdapter<User, String, UserFilter>
        implements UserRepository {

    public FirebaseUserRepositoryAdapter(Firestore firestore) {
        super(User.class, new UserFilterBuilder(), firestore);
    }

    @Override
    protected String collectionName() {
        return TableNames.USERS;
    }

    @Override
    protected String getId(User entity) {
        return entity.getId();
    }

    @Override
    public Optional<User> findByEmailOrUsername(String value) {
        QuerySnapshot emailSnapshot = await(
                firestore.collection(collectionName())
                        .whereEqualTo(UserFields.EMAIL, value)
                        .get()
        );
        if (!emailSnapshot.isEmpty()) {
            return Optional.of(emailSnapshot.getDocuments().getFirst().toObject(User.class));
        }

        QuerySnapshot usernameSnapshot = await(
                firestore.collection(collectionName())
                        .whereEqualTo(UserFields.USERNAME, value)
                        .get()
        );
        if (!usernameSnapshot.isEmpty()) {
            return Optional.of(usernameSnapshot.getDocuments().getFirst().toObject(User.class));
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        QuerySnapshot snapshot = await(
                firestore.collection(collectionName())
                        .whereEqualTo(UserFields.EMAIL, email)
                        .get()
        );
        if (!snapshot.isEmpty()) {
            return Optional.of(snapshot.getDocuments().getFirst().toObject(User.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        QuerySnapshot snapshot = await(
                firestore.collection(collectionName())
                        .whereEqualTo(UserFields.USERNAME, username)
                        .get()
        );
        if (!snapshot.isEmpty()) {
            return Optional.of(snapshot.getDocuments().getFirst().toObject(User.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        QuerySnapshot snapshot = await(
                firestore.collection(collectionName())
                        .whereEqualTo(UserFields.PHONE_NUMBER, phoneNumber)
                        .get()
        );
        if (!snapshot.isEmpty()) {
            return Optional.of(snapshot.getDocuments().getFirst().toObject(User.class));
        }
        return Optional.empty();
    }

}
