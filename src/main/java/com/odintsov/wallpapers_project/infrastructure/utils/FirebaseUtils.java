package com.odintsov.wallpapers_project.infrastructure.utils;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteBatch;
import com.odintsov.wallpapers_project.infrastructure.exceptions.FirebaseAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public static <T> List<T> saveAll(Firestore firestore, String collectionName, List<T> entities, IdExtractor<T> idExtractor) {
        WriteBatch batch = firestore.batch();
        for (T entity : entities) {
            batch.set(firestore.collection(collectionName).document(idExtractor.getId(entity)), entity);
        }
        await(batch.commit());
        return entities;
    }

    public static long count(Firestore firestore, String collectionName) {
        QuerySnapshot snapshot = await(firestore.collection(collectionName).get());
        return snapshot.size();
    }

    @FunctionalInterface
    public interface IdExtractor<T> {
        String getId(T entity);
    }

}
