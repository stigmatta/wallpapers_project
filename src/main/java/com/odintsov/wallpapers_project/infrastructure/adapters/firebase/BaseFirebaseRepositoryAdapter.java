package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.*;
import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.repositories.CrudRepository;
import com.odintsov.wallpapers_project.infrastructure.interfaces.FirestoreFilterBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils.await;
import static com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils.getOrCreateId;


/**
 * Generic Firestore adapter providing a bridge between the Domain Repository
 * and Google Cloud NoSQL storage.
 * <p>
 * This class handles the complexity of Firestore document mapping,
 * asynchronous future resolution, and paginated filtering.
 * /**
 *
 * @param <T>  Domain Entity
 * @param <ID> ID type
 * @param <F>  Filter type
 */
public abstract class BaseFirebaseRepositoryAdapter<T, ID, F>
        implements CrudRepository<T, ID, F> {


    protected final Firestore firestore;
    protected final Class<T> entityClass;
    protected final FirestoreFilterBuilder<F> filterBuilder;
    protected String typeIdPath;
    protected String typeIdValue;
    private Supplier<String> typeIdSupplier;

    protected BaseFirebaseRepositoryAdapter(
            Class<T> entityClass,
            FirestoreFilterBuilder<F> filterBuilder,
            Firestore firestore
    ) {
        this.firestore = firestore;
        this.entityClass = entityClass;
        this.filterBuilder = filterBuilder;
    }

    protected abstract String collectionName();

    protected CollectionReference getCollection() {
        return firestore.collection(collectionName());
    }

    protected abstract String getId(T entity);

    protected void setTypeDiscriminator(String path, Supplier<String> supplier) {
        this.typeIdPath = path;
        this.typeIdSupplier = supplier;
    }

    protected Query applyTypeFilter(Query query) {
        if (typeIdValue == null && typeIdSupplier != null) {
            this.typeIdValue = typeIdSupplier.get();
        }

        if (typeIdPath != null && typeIdValue != null) {
            return query.whereEqualTo(typeIdPath, typeIdValue);
        }
        return query;
    }

    private Page<T> toPage(Query query, Pageable pageable) {
        QuerySnapshot fullSnapshot = await(query.get());
        long total = fullSnapshot.size();

        Query pagedQuery = query
                .offset((int) pageable.getOffset())
                .limit(pageable.getPageSize());

        QuerySnapshot pageSnapshot = await(pagedQuery.get());

        List<T> content = pageSnapshot.getDocuments()
                .stream()
                .map(d -> d.toObject(entityClass))
                .toList();

        return new PageImpl<>(content, pageable, total);
    }


    @Override
    public Optional<T> findById(ID id) {
        try {
            DocumentSnapshot doc = await(
                    firestore.collection(collectionName())
                            .document(id.toString())
                            .get()
            );

            if (!doc.exists()) return Optional.empty();

            return Optional.ofNullable(doc.toObject(entityClass));
        } catch (Exception e) {
            throw new RuntimeException("Deserialization failed for " + entityClass.getSimpleName() +
                    " with ID: " + id + ". Check data types (Float vs Double)!", e);
        }
    }

    @Override
    public List<T> findAll() {
        Query query = applyTypeFilter(firestore.collection(collectionName()));
        QuerySnapshot snapshot = await(query.get());

        return snapshot.getDocuments()
                .stream()
                .map(d -> d.toObject(entityClass))
                .toList();
    }

    @Override
    public <S extends T> S save(S entity) {
        String id = getOrCreateId(entity);

        await(
                firestore.collection(collectionName())
                        .document(id)
                        .set(entity)
        );

        return entity;
    }


    @Override
    public void saveAll(List<? extends T> entities) {
        WriteBatch batch = firestore.batch();

        for (T entity : entities) {
            String id = getOrCreateId(entity);

            batch.set(
                    firestore.collection(collectionName())
                            .document(id),
                    entity
            );
        }

        await(batch.commit());
    }


    @Override
    public void delete(ID id) {
        await(
                firestore.collection(collectionName())
                        .document(id.toString())
                        .delete()
        );
    }

    @Override
    public long count() {
        Query query = applyTypeFilter(firestore.collection(collectionName()));
        return await(query.get()).size();
    }


    @Override
    public Page<T> findAll(Pageable pageable) {
        Query query = applyTypeFilter(firestore.collection(collectionName()));
        return toPage(query, pageable);
    }


    @Override
    public Page<T> filter(F filter, Pageable pageable) {
        Query baseQuery = applyTypeFilter(firestore.collection(collectionName()));
        Query filteredQuery = filterBuilder.apply(baseQuery, filter);
        return toPage(filteredQuery, pageable);
    }

    public Optional<T> findBySlug(String slug) {
        Query query = applyTypeFilter(firestore.collection(collectionName()))
                .whereEqualTo(CommonFields.SLUG, slug)
                .limit(1);

        var snapshot = await(query.get());

        if (snapshot.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(
                snapshot.getDocuments().getFirst().toObject(entityClass)
        );
    }

    @Override
    public void flush() {
        // NO-OP: Firebase has no persistence context
    }

}
