package com.odintsov.wallpapers_project.domain.repositories;


import com.odintsov.wallpapers_project.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmailOrUsername(String value);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByPhoneNumber(String phoneNumber);

}
