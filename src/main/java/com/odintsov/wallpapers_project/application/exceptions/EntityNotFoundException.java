package com.odintsov.wallpapers_project.application.exceptions;

/**
 * Exception thrown when a requested resource or entity cannot be found in the persistence layer.
 * <p>
 * This exception typically results in a 404 Not Found HTTP status when caught
 * by an application-level exception handler.
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     * Constructs a new exception with a default message containing the entity identifier.
     *
     * @param id the unique identifier (e.g., Long, UUID, String) of the missing entity
     */
    public EntityNotFoundException(Object id) {
        super("Entity not found with id: " + id);
    }

    /**
     * Constructs a new exception with a custom detailed message.
     *
     * @param message the specific error message
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}