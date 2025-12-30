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

@Entity
@Table(name = TableNames.USER_SESSIONS)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSession {
    @Id
    @Column(name = SessionFields.TOKEN)
    private String token;

    @Column(name = IdFields.USER_ID)
    private UUID userId;

    @Column(name = CommonFields.CREATED_AT)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = SessionFields.EXPIRES_AT)
    private LocalDateTime expiresAt;

    public UserSession(UUID userId, String token) {
        this.userId = userId;
        this.token = token;
        this.createdAt = LocalDateTime.now();
    }

    public UserSession withCreatedAndExpires(LocalDateTime created, LocalDateTime expires) {
        this.createdAt = created;
        this.expiresAt = expires;
        return this;
    }


}

