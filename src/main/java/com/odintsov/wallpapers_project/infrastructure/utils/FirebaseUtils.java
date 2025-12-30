package com.odintsov.wallpapers_project.infrastructure.utils;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.odintsov.wallpapers_project.domain.enums.NestedFields;
import com.odintsov.wallpapers_project.infrastructure.exceptions.FirebaseAccessException;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.ExecutionException;


/**
 * Utility class for Firestore operations and data transformation.
 * <p>
 * This class provides synchronous wrappers for asynchronous Firebase calls,
 * reflection-based ID management for entities, and standardized date-time
 * parsing for NoSQL document consistency.
 * </p>
 */
public final class FirebaseUtils {

    private FirebaseUtils() {
    }

    public static <R> R await(ApiFuture<R> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new FirebaseAccessException("Firebase operation interrupted", e);
        } catch (ExecutionException e) {
            if (e.getCause() != null) {
                System.err.println("!!! FIRESTORE ERROR: " + e.getCause().getMessage());
            }
            throw new FirebaseAccessException("Firebase operation failed: " + e.getMessage(), e);
        }
    }

    public static <T> List<T> findAll(Firestore firestore, String collectionName, Class<T> clazz) {
        QuerySnapshot snapshot = await(firestore.collection(collectionName).get());
        List<T> result = new ArrayList<>();
        for (QueryDocumentSnapshot doc : snapshot.getDocuments()) {
            result.add(doc.toObject(clazz));
        }
        return result;
    }

    public static <T> T save(Firestore firestore, String collectionName, T entity) {
        String id = getOrCreateId(entity);

        await(firestore.collection(collectionName).document(id).set(entity));

        return entity;
    }

    public static <T> Optional<T> findById(Firestore firestore, String collectionName, Class<T> clazz, String id) {
        if (id == null || id.isBlank()) {
            return Optional.empty();
        }

        try {
            DocumentSnapshot doc = await(
                    firestore.collection(collectionName)
                            .document(id)
                            .get()
            );

            if (!doc.exists()) {
                return Optional.empty();
            }

            return Optional.ofNullable(doc.toObject(clazz));
        } catch (Exception e) {
            throw new FirebaseAccessException("Deserialization failed for " + clazz.getSimpleName() +
                    " with ID: " + id, e);
        }
    }

    public static <T> List<T> saveAll(Firestore firestore, String collectionName, List<T> entities) {
        WriteBatch batch = firestore.batch();
        for (T entity : entities) {
            String id = getOrCreateId(entity);
            batch.set(firestore.collection(collectionName).document(id), entity);
        }
        await(batch.commit());
        return entities;
    }

    public static long count(Firestore firestore, String collectionName) {
        QuerySnapshot snapshot = await(firestore.collection(collectionName).get());
        return snapshot.size();
    }

    public static <T> String getOrCreateId(T entity) {
        try {
            Method getId = entity.getClass().getMethod("getId");
            Method setId = entity.getClass().getMethod("setId", String.class);

            String id = (String) getId.invoke(entity);

            if (id == null) {
                id = UUID.randomUUID().toString();
                setId.invoke(entity, id);
            }
            return id;

        } catch (Exception e) {
            throw new IllegalStateException("Entity must have getId()/setId()", e);
        }
    }

    public static LocalDateTime parseDateTime(String dateStr) {
        try {
            return OffsetDateTime.parse(dateStr)
                    .atZoneSameInstant(ZoneId.systemDefault())
                    .toLocalDateTime();
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(dateStr);
        }
    }

    public static <T> List<T> findByProductTypeId(Firestore firestore, String collectionName, String productTypeId, Class<T> clazz) {
        QuerySnapshot snapshot = await(
                firestore.collection(collectionName)
                        .whereEqualTo(NestedFields.PRODUCT_TYPE_ID, productTypeId)
                        .get()
        );

        if (snapshot.isEmpty()) {
            return Collections.emptyList();
        }

        return snapshot.getDocuments().stream()
                .map(doc -> doc.toObject(clazz))
                .toList();
    }

}
