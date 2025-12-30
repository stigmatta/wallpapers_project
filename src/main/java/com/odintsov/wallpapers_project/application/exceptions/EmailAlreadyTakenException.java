package com.odintsov.wallpapers_project.application.exceptions;

/**
 * Exception thrown when a user registration or update fails because the
 * provided email address is already associated with an existing account.
 * <p>
 * This is a specific subtype of {@link UserCannotBeCreatedException} used
 * to indicate a data integrity conflict.
 */
public class EmailAlreadyTakenException extends UserCannotBeCreatedException {

    /**
     * Constructs a new EmailAlreadyTakenException with a formatted message
     * containing the conflicting email.
     *
     * @param email the email address that is already in use
     */
    public EmailAlreadyTakenException(String email) {
        super("email already taken (" + email + ")");
    }
}