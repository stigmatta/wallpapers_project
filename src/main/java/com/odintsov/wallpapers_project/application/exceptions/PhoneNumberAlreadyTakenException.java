package com.odintsov.wallpapers_project.application.exceptions;

/**
 * Exception thrown when a user cannot be created or updated because the
 * provided phone number is already registered to another account.
 * <p>
 * This exception helps enforce the unique constraint on phone numbers
 * within the application's user identity system.
 */
public class PhoneNumberAlreadyTakenException extends UserCannotBeCreatedException {

    /**
     * Constructs a new PhoneNumberAlreadyTakenException with a message
     * identifying the conflicting phone number.
     *
     * @param phoneNumber the phone number that already exists in the system
     */
    public PhoneNumberAlreadyTakenException(String phoneNumber) {
        super("phone number already taken (" + phoneNumber + ")");
    }
}