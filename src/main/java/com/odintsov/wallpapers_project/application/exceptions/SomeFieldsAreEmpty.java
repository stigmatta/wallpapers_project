package com.odintsov.wallpapers_project.application.exceptions;

/**
 * Exception thrown when a user creation request fails due to missing required information.
 * <p>
 * This exception serves as a general validation failure signal when one or more
 * mandatory fields (such as username, password, or contact details) are null
 * or empty strings.
 */
public class SomeFieldsAreEmpty extends UserCannotBeCreatedException {

    /**
     * Constructs a new SomeFieldsAreEmpty exception with a default
     * validation error message.
     */
    public SomeFieldsAreEmpty() {
        super("Some fields are empty");
    }
}