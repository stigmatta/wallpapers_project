package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirFilter;
import com.odintsov.wallpapers_project.domain.entities.Souvenir;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.repositories.SouvenirRepository;
import com.odintsov.wallpapers_project.infrastructure.filterBuilders.SouvenirFilterBuilder;
import com.odintsov.wallpapers_project.infrastructure.utils.SlugUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary

public class FirebaseSouvenirRepositoryAdapter
        extends BaseFirebaseRepositoryAdapter<Souvenir, String, SouvenirFilter>
        implements SouvenirRepository {

    protected final Firestore firestore;

    public FirebaseSouvenirRepositoryAdapter() {
        super(Souvenir.class, new SouvenirFilterBuilder());
        this.firestore = FirestoreClient.getFirestore();
    }

    @Override
    protected String collectionName() {
        return TableNames.SOUVENIRS;
    }

    @Override
    protected String getId(Souvenir entity) {
        return entity.getId();
    }

    @Override
    public Souvenir save(Souvenir entity) {
        SlugUtils.generateSlugIfMissing(entity);
        return super.save(entity);
    }

    @Override
    public List<Souvenir> saveAll(List<Souvenir> entities) {
        for (Souvenir entity : entities) {
            SlugUtils.generateSlugIfMissing(entity);
        }
        return super.saveAll(entities);
    }

}