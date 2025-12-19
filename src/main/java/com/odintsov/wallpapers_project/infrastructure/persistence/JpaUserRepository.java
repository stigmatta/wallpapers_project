package com.odintsov.wallpapers_project.infrastructure.persistence;

import com.odintsov.wallpapers_project.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaUserRepository
        extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    @Query("SELECT u FROM User u WHERE u.email = :value OR u.username = :value")
    Optional<User> findByEmailOrUsername(@Param("value") String value);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber")
    Optional<User> findBuPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
