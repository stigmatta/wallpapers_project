package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.odintsov.wallpapers_project.domain.entities.ProductType;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.repositories.ProductTypeRepository;
import com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Profile("firebase")
public class FirebaseProductTypeRepositoryAdapter implements ProductTypeRepository {

    protected final Firestore firestore;

    public FirebaseProductTypeRepositoryAdapter() {
        this.firestore = FirestoreClient.getFirestore();
    }

    private String collectionName() {
        return TableNames.PRODUCT_TYPES;
    }

    private String getId(ProductType entity) {
        return entity.getId();
    }

    public List<ProductType> saveAll(List<ProductType> entities) {
        return FirebaseUtils.saveAll(firestore, collectionName(), entities);
    }

    @Override
    public Optional<ProductType> findByName(String name) {
        return FirebaseUtils.findByName(firestore, collectionName(), ProductType.class, name);
    }

    @Override
    public long count() {
        return FirebaseUtils.count(firestore, collectionName());
    }

    @Override
    public List<ProductType> findAll() {
        return FirebaseUtils.findAll(firestore, collectionName(), ProductType.class);
    }
}
