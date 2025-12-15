package com.odintsov.wallpapers_project.infrastructure.security;

import com.odintsov.wallpapers_project.application.exceptions.EntityNotFoundException;
import com.odintsov.wallpapers_project.application.exceptions.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Void> handleInvalidCredentials() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleEntityNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

