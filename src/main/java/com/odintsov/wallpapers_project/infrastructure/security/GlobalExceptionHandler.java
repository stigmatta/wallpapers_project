package com.odintsov.wallpapers_project.infrastructure.security;

import com.odintsov.wallpapers_project.application.exceptions.*;
import com.odintsov.wallpapers_project.infrastructure.exceptions.FirebaseAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * Global Interceptor for handling exceptions across the entire application.
 * <p>
 * This class uses Spring's {@code @RestControllerAdvice} to catch exceptions thrown by
 * Controller methods, Service layers, or Infrastructure adapters and transform them
 * into standardized HTTP {@link ResponseEntity} objects.
 * </p>
 * * <p><b>Architecture Role:</b> Acts as an Infrastructure-layer bridge that prevents
 * internal stack traces and domain-specific logic from leaking to the client,
 * ensuring a consistent API contract.</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles cases where a requested resource does not exist in the persistence store.
     * * @return 404 Not Found
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleEntityNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Handles authentication and authorization failures, such as incorrect passwords
     * or expired session tokens.
     * * @return 403 Forbidden
     */
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Void> handleInvalidCredentials() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    /**
     * Handles attempts to register or update data with values that must be unique
     * (Email, Username, or Phone Number) but are already present in the database.
     * * @param ex The specific conflict exception containing the error message.
     * @return 409 Conflict with a JSON body explaining the specific collision.
     */
    @ExceptionHandler({
            EmailAlreadyTakenException.class,
            UsernameAlreadyTakenException.class,
            PhoneNumberAlreadyTakenException.class
    })
    public ResponseEntity<Map<String, String>> handleConflictExceptions(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", "Conflict", "message", ex.getMessage()));
    }

    /**
     * Handles basic validation failures where required fields are missing from
     * the request payload.
     * * @param ex Exception containing details about missing fields.
     * @return 400 Bad Request
     */
    @ExceptionHandler(SomeFieldsAreEmpty.class)
    public ResponseEntity<Map<String, String>> handleEmptyFields(SomeFieldsAreEmpty ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", "Missing Data", "message", ex.getMessage()));
    }

    /**
     * Handles unexpected failures during the user registration process that are
     * not related to validation or conflicts (e.g., internal service failures).
     * * @param ex The exception describing the creation failure.
     * @return 500 Internal Server Error
     */
    @ExceptionHandler(UserCannotBeCreatedException.class)
    public ResponseEntity<Map<String, String>> handleUserCreationError(UserCannotBeCreatedException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Registration Failed", "message", ex.getMessage()));
    }

    /**
     * Handles technical failures related to the external NoSQL database connection.
     * * @param ex The infrastructure-level exception.
     * @return 503 Service Unavailable, indicating the server is alive but its
     * database dependency is unreachable.
     */
    @ExceptionHandler(FirebaseAccessException.class)
    public ResponseEntity<Map<String, String>> handleFirebaseAccess(FirebaseAccessException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(Map.of("error", "Infrastructure Error", "details", "Database is currently unreachable"));
    }

    /**
     * Catch-all handler for any {@link Exception} not specifically addressed above.
     * Prevents raw system errors from reaching the end user.
     * * @param ex The unhandled exception.
     * @return 500 Internal Server Error with a generic message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleFallback(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Unexpected Error", "message", "An internal error occurred"));
    }
}