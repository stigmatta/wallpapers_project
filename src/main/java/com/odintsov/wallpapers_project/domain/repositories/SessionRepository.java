package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.domain.entities.UserSession;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepository {

    Optional<UserSession> findByToken(String token);

    List<UserSession> findAllByUserId(UUID userId);

    void deleteByToken(String token);

    void save(UserSession session);

}
