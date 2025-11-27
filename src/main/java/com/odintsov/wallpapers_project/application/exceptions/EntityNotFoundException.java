package com.odintsov.wallpapers_project.application.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Object id) {
        super("Entity not found with id: " + id);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
