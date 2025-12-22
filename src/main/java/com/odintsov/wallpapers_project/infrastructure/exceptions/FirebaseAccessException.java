package com.odintsov.wallpapers_project.infrastructure.exceptions;

public class FirebaseAccessException extends RuntimeException {

    public FirebaseAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
