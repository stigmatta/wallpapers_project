package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.odintsov.wallpapers_project.application.dtos.User.UserFilter;
import com.odintsov.wallpapers_project.domain.entities.User;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import com.odintsov.wallpapers_project.infrastructure.filterBuilders.UserFilterBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils.await;

@Component
@Primary

public class FirebaseUserRepositoryAdapter
        extends BaseFirebaseRepositoryAdapter<User, String, UserFilter>
        implements UserRepository {

    protected final Firestore firestore;

    public FirebaseUserRepositoryAdapter() {
        super(User.class, new UserFilterBuilder());
        this.firestore = FirestoreClient.getFirestore();
    }

    @Override
    protected String collectionName() {
        return "users";
    }

    @Override
    protected String getId(User entity) {
        return entity.getId();
    }

    @Override
    public Optional<User> findByEmailOrUsername(String value) {
        QuerySnapshot emailSnapshot = await(
                firestore.collection(collectionName())
                        .whereEqualTo("email", value)
                        .get()
        );
        if (!emailSnapshot.isEmpty()) {
            return Optional.of(emailSnapshot.getDocuments().getFirst().toObject(User.class));
        }

        QuerySnapshot usernameSnapshot = await(
                firestore.collection(collectionName())
                        .whereEqualTo("username", value)
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
                        .whereEqualTo("email", email)
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
                        .whereEqualTo("username", username)
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
                        .whereEqualTo("phoneNumber", phoneNumber)
                        .get()
        );
        if (!snapshot.isEmpty()) {
            return Optional.of(snapshot.getDocuments().getFirst().toObject(User.class));
        }
        return Optional.empty();
    }

}
