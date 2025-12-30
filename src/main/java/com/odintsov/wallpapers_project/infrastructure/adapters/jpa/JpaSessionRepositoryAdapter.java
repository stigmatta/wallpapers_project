package com.odintsov.wallpapers_project.infrastructure.adapters.jpa;

import com.odintsov.wallpapers_project.domain.entities.UserSession;
import com.odintsov.wallpapers_project.domain.repositories.SessionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * JPA-based implementation of {@link SessionRepository}.
 * <p>
 * This adapter manages user sessions within a relational database. It implements
 * a "Single Session" policy during the {@code save} operation and performs
 * proactive expiration checks during token lookups.
 */
@Component
@Profile("jpa")
@Transactional
public class JpaSessionRepositoryAdapter implements SessionRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(UserSession session) {
        em.createQuery("DELETE FROM UserSession s WHERE s.userId = :userId")
                .setParameter("userId", session.getUserId())
                .executeUpdate();

        em.persist(session);
    }

    @Override
    public Optional<UserSession> findByToken(String token) {
        UserSession session = em.find(UserSession.class, token);
        if (session == null) return Optional.empty();

        if (session.getExpiresAt() != null && session.getExpiresAt().isBefore(LocalDateTime.now())) {
            em.remove(session);
            return Optional.empty();
        }

        return Optional.of(session);
    }

    @Override
    public void deleteByToken(String token) {
        UserSession session = em.find(UserSession.class, token);
        if (session != null) {
            em.remove(session);
        }
    }

    @Override
    public List<UserSession> findAllByUserId(UUID userId) {
        return em.createQuery("SELECT s FROM UserSession s WHERE s.userId = :userId", UserSession.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
