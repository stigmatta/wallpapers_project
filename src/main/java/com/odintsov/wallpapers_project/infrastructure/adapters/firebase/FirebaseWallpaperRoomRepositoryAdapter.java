package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.odintsov.wallpapers_project.domain.entities.WallpaperMaterial;
import com.odintsov.wallpapers_project.domain.entities.WallpaperRoom;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRoomRepository;
import com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Primary

public class FirebaseWallpaperRoomRepositoryAdapter
        implements WallpaperRoomRepository {

    protected final Firestore firestore;

    public FirebaseWallpaperRoomRepositoryAdapter() {
        this.firestore = FirestoreClient.getFirestore();
    }

    protected String collectionName() {
        return "wallpaper_rooms";
    }

    protected String getId(WallpaperMaterial entity) {
        return entity.getId();
    }

    @Override
    public long count() {
        return FirebaseUtils.count(firestore, collectionName());
    }

    @Override
    public List<WallpaperRoom> findAll() {
        return FirebaseUtils.findAll(firestore, collectionName(), WallpaperRoom.class);
    }

    @Override
    public List<WallpaperRoom> saveAll(List<WallpaperRoom> rooms) {
        return FirebaseUtils.saveAll(firestore, collectionName(), rooms);
    }
}
