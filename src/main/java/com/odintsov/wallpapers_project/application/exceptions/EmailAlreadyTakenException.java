package com.odintsov.wallpapers_project.application.exceptions;

public class EmailAlreadyTakenException extends UserCannotBeCreatedException {

    public EmailAlreadyTakenException(String email) {
        super("email already taken (" + email + ")");
    }
}
