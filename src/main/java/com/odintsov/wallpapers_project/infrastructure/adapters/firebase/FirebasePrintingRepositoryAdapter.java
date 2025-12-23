package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingFilter;
import com.odintsov.wallpapers_project.infrastructure.filterBuilders.PrintingFilterBuilder;
import com.odintsov.wallpapers_project.domain.entities.Printing;
import com.odintsov.wallpapers_project.domain.repositories.PrintingRepository;
import com.odintsov.wallpapers_project.infrastructure.utils.SlugUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class FirebasePrintingRepositoryAdapter
        extends BaseFirebaseRepositoryAdapter<Printing, String, PrintingFilter>
        implements PrintingRepository {

    protected final Firestore firestore;

    public FirebasePrintingRepositoryAdapter(Firestore firestore) {
        super(Printing.class, new PrintingFilterBuilder());
        this.firestore = firestore;
    }

    @Override
    protected String collectionName() {
        return "printings";
    }


    @Override
    protected String getId(Printing entity) {
        return entity.getId();
    }

    @Override
    public Printing save(Printing entity) {
        SlugUtils.generateSlugIfMissing(entity);
        return super.save(entity);
    }

    @Override
    public List<Printing> saveAll(List<Printing> entities) {
        for (Printing entity : entities) {
            SlugUtils.generateSlugIfMissing(entity);
        }
        return super.saveAll(entities);
    }

}