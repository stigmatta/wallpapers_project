package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.odintsov.wallpapers_project.domain.entities.PrintMethod;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.repositories.PrintingMethodRepository;
import com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary

public class FirebasePrintingMethodRepositoryAdapter implements PrintingMethodRepository {

    protected final Firestore firestore;

    public FirebasePrintingMethodRepositoryAdapter() {
        this.firestore = FirestoreClient.getFirestore();
    }

    private String collectionName() {
        return TableNames.PRINT_METHODS;
    }

    private String getId(PrintMethod entity) {
        return entity.getId();
    }

    public List<PrintMethod> saveAll(List<PrintMethod> entities) {
        return FirebaseUtils.saveAll(firestore, collectionName(), entities);
    }

    @Override
    public long count() {
        return FirebaseUtils.count(firestore, collectionName());
    }

    @Override
    public List<PrintMethod> findAll() {
        return FirebaseUtils.findAll(firestore, collectionName(), PrintMethod.class);
    }
}
