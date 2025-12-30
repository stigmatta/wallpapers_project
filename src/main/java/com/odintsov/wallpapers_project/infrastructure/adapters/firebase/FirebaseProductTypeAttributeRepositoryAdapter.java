package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.odintsov.wallpapers_project.domain.entities.ProductTypeAttribute;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.repositories.ProductTypeAttributeRepository;
import com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Profile("firebase")
public class FirebaseProductTypeAttributeRepositoryAdapter implements ProductTypeAttributeRepository {

    protected final Firestore firestore;

    public FirebaseProductTypeAttributeRepositoryAdapter() {
        this.firestore = FirestoreClient.getFirestore();
    }

    private String collectionName() {
        return TableNames.PRODUCT_TYPE_ATTRIBUTES;
    }

    private String getId(ProductTypeAttribute entity) {
        return entity.getId();
    }

    @Override
    public List<ProductTypeAttribute> findByProductTypeId(String productTypeId) {
        return FirebaseUtils.findByProductTypeId(firestore, collectionName(), productTypeId, ProductTypeAttribute.class);
    }

    @Override
    public long count() {
        return FirebaseUtils.count(firestore, collectionName());
    }

    @Override
    public ProductTypeAttribute save(ProductTypeAttribute attribute) {
        return FirebaseUtils.save(firestore, collectionName(), attribute);
    }

    @Override
    public void saveAll(List<ProductTypeAttribute> attributes) {
        FirebaseUtils.saveAll(firestore, collectionName(), attributes);
    }

}
