package com.odintsov.wallpapers_project.infrastructure.adapters.firebase;

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.odintsov.wallpapers_project.domain.repositories.CrudRepository;
import com.odintsov.wallpapers_project.infrastructure.interfaces.FirestoreFilterBuilder;
import com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils.await;
import static com.odintsov.wallpapers_project.infrastructure.utils.FirebaseUtils.getOrCreateId;

public abstract class BaseFirebaseRepositoryAdapter<T, ID, F>
        implements CrudRepository<T, ID, F> {

    protected final Firestore firestore;
    protected final Class<T> entityClass;
    protected final FirestoreFilterBuilder<F> filterBuilder;

    protected BaseFirebaseRepositoryAdapter(
            Class<T> entityClass,
            FirestoreFilterBuilder<F> filterBuilder
    ) {
        this.firestore = FirestoreClient.getFirestore();
        this.entityClass = entityClass;
        this.filterBuilder = filterBuilder;
    }

    protected abstract String collectionName();
    protected abstract String getId(T entity);



    @Override
    public Optional<T> findById(ID id) {
        DocumentSnapshot doc = await(
                firestore.collection(collectionName())
                        .document(id.toString())
                        .get()
        );

        return doc.exists()
                ? Optional.ofNullable(doc.toObject(entityClass))
                : Optional.empty();
    }

    @Override
    public List<T> findAll() {
        QuerySnapshot snapshot = await(
                firestore.collection(collectionName()).get()
        );

        return snapshot.getDocuments()
                .stream()
                .map(d -> d.toObject(entityClass))
                .toList();
    }

    @Override
    public T save(T entity) {
        String id = getOrCreateId(entity);

        await(
                firestore.collection(collectionName())
                        .document(id)
                        .set(entity)
        );

        return entity;
    }


    @Override
    public List<T> saveAll(List<T> entities) {
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
        return entities;
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
        QuerySnapshot snapshot = await(
                firestore.collection(collectionName()).get()
        );
        return snapshot.size();
    }


    @Override
    public Page<T> findAll(Pageable pageable) {

        Query query = firestore.collection(collectionName())
                .offset((int) pageable.getOffset())
                .limit(pageable.getPageSize());

        QuerySnapshot snapshot = await(query.get());

        List<T> content = snapshot.getDocuments()
                .stream()
                .map(d -> d.toObject(entityClass))
                .toList();

        return new PageImpl<>(content, pageable, count());
    }


    @Override
    public Page<T> filter(F filter, Pageable pageable) {

        Query baseQuery = firestore.collection(collectionName());
        Query filteredQuery = filterBuilder.apply(baseQuery, filter);

        Query pagedQuery = filteredQuery
                .offset((int) pageable.getOffset())
                .limit(pageable.getPageSize());

        QuerySnapshot snapshot = await(pagedQuery.get());

        List<T> content = snapshot.getDocuments()
                .stream()
                .map(d -> d.toObject(entityClass))
                .toList();

        long total = await(filteredQuery.get()).size();

        return new PageImpl<>(content, pageable, total);
    }

    @Override
    public void flush() {
        // NO-OP: Firebase has no persistence context
    }

    public Optional<T> findBySlug(String slug) {

        var snapshot = FirebaseUtils.await(
                firestore.collection(collectionName())
                        .whereEqualTo("slug", slug)
                        .limit(1)
                        .get()
        );

        if (snapshot.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(
                snapshot.getDocuments()
                        .getFirst()
                        .toObject(entityClass)
        );
    }

}
