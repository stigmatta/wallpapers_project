package com.odintsov.wallpapers_project.application.exceptions;

/**
 * Base abstract exception for all scenarios where a user account cannot be created.
 * <p>
 * This class provides a consistent naming convention and message structure for
 * specialized registration failures, such as duplicate data or validation errors.
 * As a {@link RuntimeException}, it allows for unchecked exception handling
 * across the application layers.
 */
public abstract class UserCannotBeCreatedException extends RuntimeException {

    /**
     * The default prefix for all error messages in this hierarchy.
     */
    private static final String BASE_MESSAGE = "User cannot be created";

    /**
     * Constructs a new exception with a detailed message.
     * <p>
     * If details are provided, the message follows the format:
     * "User cannot be created: [details]". Otherwise, it defaults to the base message.
     *
     * @param details specific information regarding why the creation failed
     */
    protected UserCannotBeCreatedException(String details) {
        super(details == null || details.isBlank()
                ? BASE_MESSAGE
                : BASE_MESSAGE + ": " + details);
    }
}