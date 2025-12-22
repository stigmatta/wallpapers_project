package com.odintsov.wallpapers_project.initializers;

import com.odintsov.wallpapers_project.domain.entities.User;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void initUsers() {
        if (userRepository.count() > 0) {
            return;
        }

        List<User> users = createUsers();
        userRepository.saveAll(users);
    }

    private List<User> createUsers() {
        User admin = new User();
        admin.setId(UUID.randomUUID().toString());
        admin.setUsername("admin");
        admin.setEmail("admin@wallpapers.com");
        admin.setHashedPassword(passwordEncoder.encode("admin123"));

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("user");
        user.setEmail("user@wallpapers.com");
        user.setHashedPassword(passwordEncoder.encode("user123"));

        return Arrays.asList(admin, user);
    }
}