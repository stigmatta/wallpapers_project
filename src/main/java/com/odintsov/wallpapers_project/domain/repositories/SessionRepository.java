package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.domain.entities.UserSession;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing {@link UserSession} persistence.
 * <p>
 * This repository is a critical component of the security infrastructure,
 * allowing the system to validate authentication tokens and manage
 * concurrent user sessions.
 */
public interface SessionRepository {

    /**
     * Retrieves a session by its unique authentication token.
     * Use this during request filtering to verify if a user is logged in.
     * * @param token the unique session identifier.
     * @return an Optional containing the session if found, otherwise empty.
     */
    Optional<UserSession> findByToken(String token);

    /**
     * Retrieves all active or stored sessions associated with a specific user.
     * Useful for security audits or "Logout from all devices" functionality.
     * * @param userId the unique identifier of the user.
     * @return a list of sessions belonging to the user.
     */
    List<UserSession> findAllByUserId(UUID userId);

    /**
     * Removes a session from the store.
     * Typically called during the logout process.
     * * @param token the token to be invalidated.
     */
    void deleteByToken(String token);

    /**
     * Persists a new or updated session record.
     * * @param session the session entity to save.
     */
    void save(UserSession session);
}