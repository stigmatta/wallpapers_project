package com.odintsov.wallpapers_project.application.exceptions;

public class PhoneNumberAlreadyTakenException extends UserCannotBeCreatedException {
    public PhoneNumberAlreadyTakenException(String phoneNumber) {
        super("phone number already taken (" + phoneNumber + ")");
    }
}
