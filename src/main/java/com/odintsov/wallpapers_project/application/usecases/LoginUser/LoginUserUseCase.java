package com.odintsov.wallpapers_project.application.usecases.LoginUser;

import com.odintsov.wallpapers_project.application.exceptions.InvalidCredentialsException;

import java.util.UUID;

/**
 * Use case responsible for authenticating a user and establishing a session.
 * <p>
 * This component coordinates finding the user, verifying their credentials,
 * and generating a unique session identifier upon success.
 */
public interface LoginUserUseCase {

    /**
     * Authenticates a user based on the provided command.
     *
     * @param command contains the username/email and password
     * @return a {@link UUID} representing the newly created session token
     * @throws InvalidCredentialsException
     * if the user is not found or the password does not match
     */
    UUID execute(LoginUserCommand command);
}