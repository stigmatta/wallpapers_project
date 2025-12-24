package com.odintsov.wallpapers_project.initializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odintsov.wallpapers_project.domain.entities.User;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import com.odintsov.wallpapers_project.initializers.dtos.UserJson;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    @Transactional
    public void initUsers() throws IOException {
        if (userRepository.count() > 0) {
            return;
        }

        List<UserJson> userData = objectMapper.readValue(
                new ClassPathResource("data/users.json").getInputStream(),
                new TypeReference<>() {}
        );

        List<User> users = userData.stream().map(data ->
                User.builder()
                        .username(data.username())
                        .email(data.email())
                        .hashedPassword(passwordEncoder.encode(data.password()))
                        .build()
        ).toList();

        userRepository.saveAll(users);
        userRepository.flush();
    }
}