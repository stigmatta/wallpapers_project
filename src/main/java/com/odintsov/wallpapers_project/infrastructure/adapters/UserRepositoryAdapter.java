package com.odintsov.wallpapers_project.infrastructure.adapters;

import com.odintsov.wallpapers_project.domain.entities.User;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter
        extends BaseJpaRepositoryAdapter<User, Long, JpaUserRepository>
        implements UserRepository {

    public UserRepositoryAdapter(JpaUserRepository jpaRepository) {
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


}
