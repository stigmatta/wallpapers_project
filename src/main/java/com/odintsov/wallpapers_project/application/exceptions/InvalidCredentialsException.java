package com.odintsov.wallpapers_project.application.exceptions;

/**
 * Exception thrown when authentication fails due to incorrect user credentials.
 * <p>
 * This exception is commonly used during the login process when a provided
 * username/email and password combination does not match any existing record.
 * For security reasons, it does not specify whether the identifier or the
 * password was the cause of the failure.
 */
public class InvalidCredentialsException extends RuntimeException {

    /**
     * Constructs a new InvalidCredentialsException with a fixed
     * security-safe error message.
     */
    public InvalidCredentialsException() {
        super("Invalid credentials are given");
    }
}