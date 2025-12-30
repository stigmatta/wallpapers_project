package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.domain.entities.UserSession;
import com.odintsov.wallpapers_project.domain.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service responsible for managing user authentication sessions.
 * <p>
 * Handles the creation, validation, and deletion of session tokens.
 * This service ensures that only one active session exists per user at a time
 * and manages session expiration logic.
 */
@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /**
     * Extracts the raw token from an HTTP Authorization header.
     *
     * @param authHeader the "Authorization" header value (expected format: "Bearer {token}")
     * @return the extracted token string
     * @throws RuntimeException if the header is null or does not start with "Bearer "
     */
    public String extractTokenFromHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }
        return authHeader.substring(7);
    }

    /**
     * Resolves a User ID from the provided authentication header.
     * <p>
     * Validates that the token exists in the database and has not expired.
     * If the session is expired, it is automatically removed from the repository.
     *
     * @param authHeader the "Authorization" header from the request
     * @return the UUID of the user associated with the session
     * @throws RuntimeException if the token is invalid or the session has expired
     */
    public UUID getUserIdByAuthHeader(String authHeader) {
        String token = extractTokenFromHeader(authHeader);

        UserSession session = sessionRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid session token"));

        if (session.getExpiresAt() != null && session.getExpiresAt().isBefore(LocalDateTime.now())) {
            sessionRepository.deleteByToken(token);
            throw new RuntimeException("Session expired");
        }

        return session.getUserId();
    }

    /**
     * Creates a new session for a user, invalidating any existing sessions first.
     *
     * @param userId     the unique identifier of the user
     * @param ttlMinutes the "Time To Live" in minutes for the session
     * @return a newly generated unique session token
     */
    public String createSession(UUID userId, long ttlMinutes) {
        // Invalidate old sessions to enforce single-session policy
        sessionRepository.findAllByUserId(userId)
                .forEach(session -> sessionRepository.deleteByToken(session.getToken()));

        String token = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        UserSession session = new UserSession();
        session.setUserId(userId);
        session.setToken(token);
        session.setCreatedAt(now);
        session.setExpiresAt(now.plusMinutes(ttlMinutes));

        sessionRepository.save(session);
        return token;
    }

    /**
     * Invalidates and removes a session token from the repository.
     *
     * @param token the session token to delete
     */
    public void deleteSession(String token) {
        sessionRepository.deleteByToken(token);
    }
}