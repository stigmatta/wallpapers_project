package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingFilter;
import com.odintsov.wallpapers_project.domain.entities.Printing;
import com.odintsov.wallpapers_project.domain.enums.NestedFields;
import com.odintsov.wallpapers_project.domain.enums.ProductTypes;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.repositories.PrintingRepository;
import com.odintsov.wallpapers_project.infrastructure.filterBuilders.PrintingFilterBuilder;
import com.odintsov.wallpapers_project.infrastructure.utils.ProductTypeRegistry;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("firebase")
@Primary
public class FirebasePrintingRepositoryAdapter
        extends BaseFirebaseRepositoryAdapter<Printing, String, PrintingFilter>
        implements PrintingRepository {


    public FirebasePrintingRepositoryAdapter(Firestore firestore, ProductTypeRegistry typeRegistry) {
        super(Printing.class, new PrintingFilterBuilder(), firestore);
        this.setTypeDiscriminator(
                NestedFields.PRODUCT_TYPE_ID,
                () -> typeRegistry.getTypeId(ProductTypes.PRINTING)
        );
    }

    @Override
    protected String collectionName() {
        return TableNames.PRODUCTS;
    }


    @Override
    protected String getId(Printing entity) {
        return entity.getId();
    }

    @Override
    public <S extends Printing> S save(S entity) {
        entity.syncMethodIds();
        entity.syncCategoryIds();
        entity.calculateEffectivePrice();
        return super.save(entity);
    }

    @Override
    public void saveAll(java.util.List<? extends Printing> entities) {
        for (Printing entity : entities) {
            entity.syncMethodIds();
            entity.syncCategoryIds();
            entity.calculateEffectivePrice();
        }
        super.saveAll(entities);
    }

}