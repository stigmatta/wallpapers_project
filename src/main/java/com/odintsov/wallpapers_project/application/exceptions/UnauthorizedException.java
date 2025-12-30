package com.odintsov.wallpapers_project.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Unauthorized access: Authentication is required or token is invalid.");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
