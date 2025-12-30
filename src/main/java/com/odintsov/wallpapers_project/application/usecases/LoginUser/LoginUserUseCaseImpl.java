package com.odintsov.wallpapers_project.application.usecases.LoginUser;

import com.odintsov.wallpapers_project.application.exceptions.InvalidCredentialsException;
import com.odintsov.wallpapers_project.application.services.SessionService;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginUserUseCaseImpl implements LoginUserUseCase {

    private final UserRepository userRepository;
    private final SessionService sessionService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UUID execute(LoginUserCommand command) {
        var user = userRepository
                .findByEmailOrUsername(command.usernameOrEmail())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(command.password(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return UUID.fromString(sessionService.createSession(UUID.fromString(user.getId()), 1));
    }
}


