package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.odintsov.wallpapers_project.domain.entities.PrintingMethodsLink;
import com.odintsov.wallpapers_project.domain.repositories.PrintingMethodLinkRepository;
import com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary

public class FirebasePrintingMethodLinkRepositoryAdapter
        implements PrintingMethodLinkRepository {

    protected final Firestore firestore;

    public FirebasePrintingMethodLinkRepositoryAdapter() {
        this.firestore = FirestoreClient.getFirestore();
    }

    protected String collectionName() {
        return "printing_method_links";
    }

    protected String getId(PrintingMethodsLink entity) {
        return entity.getId();
    }

    @Override
    public List<PrintingMethodsLink> saveAll(List<PrintingMethodsLink> entities) {
        return FirebaseUtils.saveAll(firestore, collectionName(), entities, PrintingMethodsLink::getId);
    }
}
