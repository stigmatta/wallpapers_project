package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.odintsov.wallpapers_project.domain.entities.ExtraFeature;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.repositories.ExtraFeatureRepository;
import com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@Profile("firebase")
public class FirebaseExtraFeatureRepositoryAdapter implements ExtraFeatureRepository {

    protected final Firestore firestore;

    public FirebaseExtraFeatureRepositoryAdapter() {
        this.firestore = FirestoreClient.getFirestore();
    }

    private String collectionName() {
        return TableNames.EXTRA_FEATURES;
    }

    private String getId(ExtraFeature entity) {
        return entity.getId();
    }

    @Override
    public List<ExtraFeature> findByProductTypeId(String productTypeId) {
        return FirebaseUtils.findByProductTypeId(firestore, collectionName(), productTypeId, ExtraFeature.class);
    }

    @Override
    public long count() {
        return FirebaseUtils.count(firestore, collectionName());
    }

    @Override
    public ExtraFeature save(ExtraFeature attribute) {
        return FirebaseUtils.save(firestore, collectionName(), attribute);
    }

    @Override
    public void saveAll(List<ExtraFeature> attributes) {
        FirebaseUtils.saveAll(firestore, collectionName(), attributes);
    }

    @Override
    public Optional<ExtraFeature> findById(String id) {
        return FirebaseUtils.findById(firestore, collectionName(), ExtraFeature.class, id);
    }

}
