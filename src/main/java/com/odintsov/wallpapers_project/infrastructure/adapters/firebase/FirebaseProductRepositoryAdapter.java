package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.odintsov.wallpapers_project.application.dtos.Product.ProductFilter;
import com.odintsov.wallpapers_project.domain.entities.BaseProduct;
import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.repositories.ProductRepository;
import com.odintsov.wallpapers_project.infrastructure.filterBuilders.ProductFilterBuilder;
import com.odintsov.wallpapers_project.infrastructure.utils.SlugUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils.await;

@Component
@Profile("firebase")

public class FirebaseProductRepositoryAdapter
        extends BaseFirebaseRepositoryAdapter<BaseProduct, String, ProductFilter>
        implements ProductRepository {


    public FirebaseProductRepositoryAdapter(Firestore firestore) {
        super(BaseProduct.class, new ProductFilterBuilder(), firestore);
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
        entity.syncCategoryIds();
        return super.save(entity);
    }

    @Override
    public void saveAll(List<? extends BaseProduct> entities) {
        for (BaseProduct entity : entities) {
            SlugUtils.generateSlugIfMissing(entity);
            entity.syncCategoryIds();
        }
        super.saveAll(entities);
    }

    @Override
    public List<BaseProduct> findGlobal(String name) {
        if (name == null || name.isEmpty()) return List.of();

        return await(firestore.collection(collectionName())
                .orderBy(CommonFields.NAME)
                .startAt(name)
                .endAt(name + "\uf8ff")
                .get())
                .toObjects(BaseProduct.class);
    }
}
