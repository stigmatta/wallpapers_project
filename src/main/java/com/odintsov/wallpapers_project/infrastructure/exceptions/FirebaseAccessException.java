package com.odintsov.wallpapers_project.infrastructure.exceptions;

/**
 * Exception thrown when a technical error occurs during interaction with Firebase services.
 * <p>
 * This is an unchecked exception used to wrap low-level SDK exceptions
 * (e.g., network timeouts, authentication failures, or Firestore quota issues)
 * into a standard format that the application's global exception handler can catch.
 * </p>
 */
public class FirebaseAccessException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message The descriptive error message.
     * @param cause   The underlying exception thrown by the Firebase/Google SDK.
     */
    public FirebaseAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}