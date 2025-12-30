package com.odintsov.wallpapers_project.application.exceptions;

/**
 * Exception thrown when a user registration fails because the requested
 * username is already in use by another account.
 * <p>
 * This exception ensures that the unique constraint on usernames is
 * communicated clearly to the service layer and the end user.
 */
public class UsernameAlreadyTakenException extends UserCannotBeCreatedException {

    /**
     * Constructs a new UsernameAlreadyTakenException with a message
     * identifying the unavailable username.
     *
     * @param username the username that is already registered in the system
     */
    public UsernameAlreadyTakenException(String username) {
        super("username already taken (" + username + ")");
    }
}