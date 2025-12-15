package com.odintsov.wallpapers_project.application.exceptions;

public abstract class UserCannotBeCreatedException extends RuntimeException {

    private static final String BASE_MESSAGE = "User cannot be created";

    protected UserCannotBeCreatedException(String details) {
        super(details == null || details.isBlank()
                ? BASE_MESSAGE
                : BASE_MESSAGE + ": " + details);
    }
}
