package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.odintsov.wallpapers_project.domain.entities.WallpaperMaterial;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperMaterialRepository;
import com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Profile("firebase")

public class FirebaseWallpaperMaterialRepositoryAdapter
        implements WallpaperMaterialRepository {

    protected final Firestore firestore;

    public FirebaseWallpaperMaterialRepositoryAdapter() {
        this.firestore = FirestoreClient.getFirestore();
    }

    protected String collectionName() {
        return TableNames.WALLPAPER_MATERIALS;
    }

    protected String getId(WallpaperMaterial entity) {
        return entity.getId();
    }

    @Override
    public List<WallpaperMaterial> saveAll(List<WallpaperMaterial> entities) {
        return FirebaseUtils.saveAll(firestore, collectionName(), entities);
    }

    @Override
    public long count() {
        return FirebaseUtils.count(firestore, collectionName());
    }

    @Override
    public List<WallpaperMaterial> findAll() {
        return FirebaseUtils.findAll(firestore, collectionName(), WallpaperMaterial.class);
    }
}
