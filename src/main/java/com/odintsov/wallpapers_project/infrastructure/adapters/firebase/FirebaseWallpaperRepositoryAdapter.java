package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperFilter;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import com.odintsov.wallpapers_project.domain.enums.NestedFields;
import com.odintsov.wallpapers_project.domain.enums.ProductTypes;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRepository;
import com.odintsov.wallpapers_project.infrastructure.filterBuilders.WallpaperFilterBuilder;
import com.odintsov.wallpapers_project.infrastructure.utils.ProductTypeRegistry;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("firebase")

public class FirebaseWallpaperRepositoryAdapter
        extends BaseFirebaseRepositoryAdapter<Wallpaper, String, WallpaperFilter>
        implements WallpaperRepository {

    protected final Firestore firestore;

    public FirebaseWallpaperRepositoryAdapter(ProductTypeRegistry typeRegistry) {
        super(Wallpaper.class, new WallpaperFilterBuilder());
        this.firestore = FirestoreClient.getFirestore();

        this.setTypeDiscriminator(
                NestedFields.PRODUCT_TYPE_ID,
                typeRegistry.getTypeId(ProductTypes.WALLPAPER)
        );
    }

    @Override
    protected String collectionName() {
        return TableNames.PRODUCTS;
    }

    @Override
    protected String getId(Wallpaper entity) {
        return entity.getId();
    }

}