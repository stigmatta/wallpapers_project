package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.repositories.CategoryRepository;
import com.odintsov.wallpapers_project.infrastructure.filterBuilders.NullTypeFirestoreFilterBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.lang.model.type.NullType;

@Component
@Profile("firebase")
public class FirebaseCategoryRepositoryAdapter
        extends BaseFirebaseRepositoryAdapter<Category, String, NullType>
        implements CategoryRepository {

    public FirebaseCategoryRepositoryAdapter(Firestore firestore) {
        super(Category.class, new NullTypeFirestoreFilterBuilder(), firestore);
    }

    @Override
    protected String collectionName() {
        return TableNames.CATEGORIES;
    }

    @Override
    protected String getId(Category entity) {
        return entity.getId();
    }
}

