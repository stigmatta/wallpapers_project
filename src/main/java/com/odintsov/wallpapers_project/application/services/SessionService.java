package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.domain.entities.UserSession;
import com.odintsov.wallpapers_project.domain.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /**
     * Повертає userId для переданого токена.
     * Кидає RuntimeException якщо токен недійсний або прострочений.
     */

    public String extractTokenFromHeader(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }
        return authHeader.substring(7);

    }

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
     * Створює нову сесію і повертає токен
     */
    public String createSession(UUID userId, long ttlMinutes) {
        // Видалити старі сесії
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
     * Видаляє сесію
     */
    public void deleteSession(String token) {
        sessionRepository.deleteByToken(token);
    }
}
