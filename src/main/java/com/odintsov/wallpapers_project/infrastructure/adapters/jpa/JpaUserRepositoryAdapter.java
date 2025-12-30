package com.odintsov.wallpapers_project.infrastructure.adapters.jpa;

import com.odintsov.wallpapers_project.application.dtos.User.UserFilter;
import com.odintsov.wallpapers_project.domain.entities.User;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.jpa.JpaUserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * JPA implementation of the {@link UserRepository}.
 * <p>
 * This adapter delegates standard lookups to the {@link JpaUserRepository}
 * and provides a complex {@code filter} implementation using JPA Specifications
 * for partial and case-insensitive matching.
 */
@Component
@Profile("jpa")
public class JpaUserRepositoryAdapter
        extends BaseJpaRepositoryAdapter<User, String, UserFilter, JpaUserRepository>
        implements UserRepository {

    public JpaUserRepositoryAdapter(JpaUserRepository jpaRepository) {
        super(jpaRepository);
    }

    @Override
    public Optional<User> findByEmailOrUsername(String value) {
        return jpaRepository.findByEmailOrUsername(value);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return jpaRepository.findBuPhoneNumber(phoneNumber);
    }

    @Override
    public Page<User> filter(UserFilter filter, Pageable pageable) {
        return super.filter(filter, pageable, f -> {
            if (filter == null) {
                return Specification.unrestricted();
            }

            Specification<User> spec = Specification.unrestricted();

            if (filter.getUsername() != null && !filter.getUsername().isBlank()) {
                spec = spec.and((root, query, cb) ->
                        cb.like(cb.lower(root.get("username")), "%" + filter.getUsername().toLowerCase() + "%"));
            }

            if (filter.getEmail() != null && !filter.getEmail().isBlank()) {
                spec = spec.and((root, query, cb) ->
                        cb.like(cb.lower(root.get("email")), "%" + filter.getEmail().toLowerCase() + "%"));
            }

            return spec;
        });
    }


}
