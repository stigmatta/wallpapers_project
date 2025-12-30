package com.odintsov.wallpapers_project.infrastructure.utils;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteBatch;
import com.odintsov.wallpapers_project.infrastructure.exceptions.FirebaseAccessException;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
            throw new FirebaseAccessException("Firebase operation failed", e);
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


}
