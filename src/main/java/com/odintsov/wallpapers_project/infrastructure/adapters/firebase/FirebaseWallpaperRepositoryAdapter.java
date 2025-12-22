package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperFilter;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRepository;
import com.odintsov.wallpapers_project.infrastructure.filterBuilders.WallpaperFilterBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary

public class FirebaseWallpaperRepositoryAdapter
        extends BaseFirebaseRepositoryAdapter<Wallpaper, String, WallpaperFilter>
        implements WallpaperRepository {

    protected final Firestore firestore;

    public FirebaseWallpaperRepositoryAdapter() {
        super(Wallpaper.class, new WallpaperFilterBuilder());
        this.firestore = FirestoreClient.getFirestore();
    }

    @Override
    protected String collectionName() {
        return "wallpapers";
    }

    @Override
    protected String getId(Wallpaper entity) {
        return entity.getId();
    }

}