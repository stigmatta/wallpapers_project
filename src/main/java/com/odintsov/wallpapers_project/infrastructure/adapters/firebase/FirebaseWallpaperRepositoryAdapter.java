package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperFilter;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRepository;
import com.odintsov.wallpapers_project.infrastructure.filterBuilders.WallpaperFilterBuilder;
import com.odintsov.wallpapers_project.infrastructure.utils.SlugUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

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
        return TableNames.WALLPAPERS;
    }

    @Override
    protected String getId(Wallpaper entity) {
        return entity.getId();
    }

    @Override
    public Wallpaper save(Wallpaper entity) {
        SlugUtils.generateSlugIfMissing(entity);
        return super.save(entity);
    }

    @Override
    public List<Wallpaper> saveAll(List<Wallpaper> entities) {
        for (Wallpaper entity : entities) {
            SlugUtils.generateSlugIfMissing(entity);
        }
        return super.saveAll(entities);
    }

}