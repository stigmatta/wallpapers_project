package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.application.dtos.User.UserFilter;
import com.odintsov.wallpapers_project.domain.entities.User;
import java.util.Optional;

/**
 * Repository interface for {@link User} entity operations.
 * <p>
 * Extends {@link CrudRepository} to provide standard persistence
 * functionality and adds specialized lookup methods for identity
 * verification and authentication.
 */
public interface UserRepository extends CrudRepository<User, String, UserFilter> {

    /**
     * Finds a user by either their email address or their username.
     * Primarily used in login flows where a user may provide either identifier.
     * * @param value the email or username string.
     * @return an Optional containing the user if a match is found.
     */
    Optional<User> findByEmailOrUsername(String value);

    /**
     * Retrieves a user by their unique email.
     */
    Optional<User> findByEmail(String email);

    /**
     * Retrieves a user by their unique username.
     */
    Optional<User> findByUsername(String username);

    /**
     * Retrieves a user by their unique phone number.
     * Useful for SMS-based verification or multi-factor authentication.
     */
    Optional<User> findByPhoneNumber(String phoneNumber);
}