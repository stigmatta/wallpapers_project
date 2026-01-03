package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirFilter;
import com.odintsov.wallpapers_project.domain.entities.Souvenir;
import com.odintsov.wallpapers_project.domain.enums.NestedFields;
import com.odintsov.wallpapers_project.domain.enums.ProductTypes;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.repositories.SouvenirRepository;
import com.odintsov.wallpapers_project.infrastructure.filterBuilders.SouvenirFilterBuilder;
import com.odintsov.wallpapers_project.infrastructure.utils.ProductTypeRegistry;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Component
@Profile("firebase")

public class FirebaseSouvenirRepositoryAdapter
        extends BaseFirebaseRepositoryAdapter<Souvenir, String, SouvenirFilter>
        implements SouvenirRepository {

    protected final Firestore firestore;

    public FirebaseSouvenirRepositoryAdapter(Firestore firestore, ProductTypeRegistry typeRegistry) {
        super(Souvenir.class, new SouvenirFilterBuilder(), firestore);
        this.firestore = FirestoreClient.getFirestore();
        this.setTypeDiscriminator(
                NestedFields.PRODUCT_TYPE_ID,
                typeRegistry.getTypeId(ProductTypes.SOUVENIR)
        );
    }

    @Override
    protected String collectionName() {
        return TableNames.PRODUCTS;
    }

    @Override
    protected String getId(Souvenir entity) {
        return entity.getId();
    }

}