package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.odintsov.wallpapers_project.domain.entities.Order;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.repositories.OrderRepository;
import com.odintsov.wallpapers_project.infrastructure.adapters.firebase.models.OrderDocument;
import com.odintsov.wallpapers_project.infrastructure.mappers.FirebaseOrderMapper;
import com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@Profile("firebase")
public class FirebaseOrderRepositoryAdapter implements OrderRepository {

    protected final Firestore firestore;
    private final CollectionReference collection;
    private final FirebaseOrderMapper mapper;

    public FirebaseOrderRepositoryAdapter(FirebaseOrderMapper mapper) {
        this.mapper = mapper;
        this.firestore = FirestoreClient.getFirestore();
        this.collection = firestore.collection(collectionName());
    }

    protected String collectionName() {
        return TableNames.ORDERS;
    }

    protected String getId(Order entity) {
        return entity.getId();
    }

    @Override
    public Order save(Order order) {
        String id = FirebaseUtils.getOrCreateId(order);

        order.getItems().forEach(item -> {
            FirebaseUtils.getOrCreateId(item);
            item.getOrderItemExtraFeatures().forEach(feature -> {
                if (feature.getExtraFeature() != null) {
                    FirebaseUtils.getOrCreateId(feature.getExtraFeature());
                }
            });
        });

        OrderDocument document = mapper.toDocument(order);
        FirebaseUtils.await(
                collection.document(id).set(document)
        );
        return order;
    }

    @Override
    public Optional<Order> findById(String id) {
        if (id == null || id.isBlank()) return Optional.empty();

        DocumentSnapshot doc = FirebaseUtils.await(collection.document(id).get());

        if (!doc.exists()) return Optional.empty();

        OrderDocument document = doc.toObject(OrderDocument.class);

        return Optional.ofNullable(mapper.toDomain(document));
    }

    @Override
    public List<Order> findByUserId(String userId) {
        Query query = collection.whereEqualTo(IdFields.USER_ID, userId);
        QuerySnapshot snapshot = FirebaseUtils.await(query.get());

        return snapshot.getDocuments().stream()
                .map(doc -> doc.toObject(OrderDocument.class))
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Order> findAll() {
        QuerySnapshot snapshot = FirebaseUtils.await(collection.get());

        return snapshot.getDocuments().stream()
                .map(doc -> doc.toObject(OrderDocument.class))
                .map(mapper::toDomain)
                .toList();
    }
}
