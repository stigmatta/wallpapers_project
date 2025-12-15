package com.odintsov.wallpapers_project.application.exceptions;

public class UsernameAlreadyTakenException extends UserCannotBeCreatedException {

    public UsernameAlreadyTakenException(String username) {
        super("username already taken (" + username + ")");
    }
}
