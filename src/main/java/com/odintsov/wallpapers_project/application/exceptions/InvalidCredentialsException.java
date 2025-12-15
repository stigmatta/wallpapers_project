package com.odintsov.wallpapers_project.application.exceptions;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Invalid credentials are given");
    }
}
