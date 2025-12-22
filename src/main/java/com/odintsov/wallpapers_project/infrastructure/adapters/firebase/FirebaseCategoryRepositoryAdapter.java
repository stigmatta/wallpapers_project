package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.odintsov.wallpapers_project.domain.entities.Category;
import com.odintsov.wallpapers_project.domain.repositories.CategoryRepository;
import com.odintsov.wallpapers_project.infrastructure.filterBuilders.NullTypeFirestoreFilterBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.lang.model.type.NullType;

@Component
@Primary
public class FirebaseCategoryRepositoryAdapter
        extends BaseFirebaseRepositoryAdapter<Category, String, NullType>
        implements CategoryRepository {

    public FirebaseCategoryRepositoryAdapter() {
        super(Category.class, new NullTypeFirestoreFilterBuilder());
    }

    @Override
    protected String collectionName() {
        return "categories";
    }

    @Override
    protected String getId(Category entity) {
        return entity.getId();
    }
}

