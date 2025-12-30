package com.odintsov.wallpapers_project.domain.entities;

import com.odintsov.wallpapers_project.domain.enums.CommonFields;
import com.odintsov.wallpapers_project.domain.enums.TableNames;
import com.odintsov.wallpapers_project.domain.enums.UserFields;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a registered user within the system.
 * <p>
 * This class serves as the principal for authentication and authorization.
 * It stores unique identifiers (email, username, phone) and maintains
 * the lifecycle of all {@link Order} records associated with the individual.
 */
@Entity
@Table(name = TableNames.USERS)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    /**
     * Unique identifier for the user, generated as a UUID string.
     */
    @Id
    @UuidGenerator
    @Column(name = CommonFields.ID, updatable = false, nullable = false, length = 36, columnDefinition = "VARCHAR2(36)")
    protected String id;

    /**
     * Primary contact email, used for login and notifications.
     * Must be unique across the system.
     */
    @Column(name = UserFields.EMAIL, nullable = false, unique = true)
    private String email;

    /**
     * Unique display name or login handle for the user.
     */
    @Column(name = UserFields.USERNAME, nullable = false, unique = true)
    private String username;

    /**
     * Unique telephone number for account verification or delivery contact.
     */
    @Column(name = UserFields.PHONE_NUMBER, nullable = false, unique = true)
    private String phoneNumber;

    /**
     * Encoded password hash.
     * <p>Note: Should always be stored using a secure hashing algorithm
     * like BCrypt or Argon2.</p>
     */
    @Column(name = UserFields.PASSWORD, nullable = false)
    private String password;

    /**
     * The history of all active and past orders placed by this user.
     * <p>
     * Managed via composition; deleting a user will remove all associated
     * orders and their details.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Order> orders = new ArrayList<>();
}