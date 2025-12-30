package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.odintsov.wallpapers_project.application.dtos.Product.ProductFilter;
import com.odintsov.wallpapers_project.domain.entities.BaseProduct;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.repositories.ProductRepository;
import com.odintsov.wallpapers_project.infrastructure.filterBuilders.ProductFilterBuilder;
import com.odintsov.wallpapers_project.infrastructure.utils.SlugUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("firebase")

public class FirebaseProductRepositoryAdapter
        extends BaseFirebaseRepositoryAdapter<BaseProduct, String, ProductFilter>
        implements ProductRepository {

    protected final Firestore firestore;

    public FirebaseProductRepositoryAdapter() {
        super(BaseProduct.class, new ProductFilterBuilder());
        this.firestore = FirestoreClient.getFirestore();
    }

    @Override
    protected String collectionName() {
        return TableNames.PRODUCTS;
    }

    @Override
    protected String getId(BaseProduct entity) {
        return entity.getId();
    }

    @Override
    public <S extends BaseProduct> S save(S entity) {
        SlugUtils.generateSlugIfMissing(entity);
        return super.save(entity);
    }

    @Override
    public void saveAll(List<? extends BaseProduct> entities) {
        for (BaseProduct entity : entities) {
            SlugUtils.generateSlugIfMissing(entity);
        }
        super.saveAll(entities);
    }
}
