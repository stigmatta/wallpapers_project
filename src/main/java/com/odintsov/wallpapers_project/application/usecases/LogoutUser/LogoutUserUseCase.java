package com.odintsov.wallpapers_project.application.usecases.LogoutUser;

/**
 * Use case responsible for terminating a user session.
 * <p>
 * This component invalidates the session token provided in the authorization header,
 * effectively logging the user out of the system.
 */
public interface LogoutUserUseCase {

    /**
     * Terminates the current session based on the provided authorization header.
     *
     * @param authHeader the "Authorization" header containing the Bearer token
     * @throws RuntimeException if the header is malformed or missing
     */
    void execute(String authHeader);
}