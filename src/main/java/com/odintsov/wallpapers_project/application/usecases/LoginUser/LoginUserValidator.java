package com.odintsov.wallpapers_project.application.usecases.LoginUser;

import com.odintsov.wallpapers_project.application.exceptions.InvalidCredentialsException;
import com.odintsov.wallpapers_project.domain.entities.User;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUserValidator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User validate(LoginUserCommand command) {

        if (command.usernameOrEmail() == null || command.password() == null ||
                command.usernameOrEmail().isBlank() || command.password().isBlank()) {
            throw new InvalidCredentialsException();
        }

        User user = userRepository
                .findByEmailOrUsername(command.usernameOrEmail())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(command.password(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return user;
    }
}

