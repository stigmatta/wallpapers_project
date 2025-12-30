package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.IdFields;
import com.odintsov.wallpapers_project.domain.enums.SessionFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing an active authentication session for a user.
 * <p>
 * This class tracks the lifecycle of a user's login state. Sessions are
 * identified by a unique token and are constrained by an expiration timestamp.
 * They serve as a server-side record to validate incoming requests.
 */
@Entity
@Table(name = TableNames.USER_SESSIONS)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSession {

    /**
     * The unique session identifier (token) provided to the client.
     * Acts as the primary key for rapid session lookup.
     */
    @Id
    @Column(name = SessionFields.TOKEN)
    private String token;

    /**
     * The unique identifier of the user who owns this session.
     */
    @Column(name = IdFields.USER_ID)
    private UUID userId;

    /**
     * Timestamp indicating when the session was initialized.
     */
    @Builder.Default
    @Column(name = CommonFields.CREATED_AT)
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Timestamp indicating when the session is no longer valid.
     */
    @Column(name = SessionFields.EXPIRES_AT)
    private LocalDateTime expiresAt;

    /**
     * Convenience constructor for initializing a new session.
     * * @param userId the owner of the session
     *
     * @param token the generated session token
     */
    public UserSession(UUID userId, String token) {
        this.userId = userId;
        this.token = token;
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Fluent API method to set both temporal fields.
     * * @param created timestamp of creation
     *
     * @param expires timestamp of expiration
     * @return the updated {@link UserSession} instance
     */
    public UserSession withCreatedAndExpires(LocalDateTime created, LocalDateTime expires) {
        this.createdAt = created;
        this.expiresAt = expires;
        return this;
    }
}