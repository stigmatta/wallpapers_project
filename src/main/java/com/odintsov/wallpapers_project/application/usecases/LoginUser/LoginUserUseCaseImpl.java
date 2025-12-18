package com.odintsov.wallpapers_project.application.usecases.LoginUser;

import com.odintsov.wallpapers_project.application.exceptions.InvalidCredentialsException;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginUserUseCaseImpl implements LoginUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void execute(LoginUserCommand command) {
        var user = userRepository
                .findByEmailOrUsername(command.usernameOrEmail())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(command.password(), user.getHashedPassword())) {
            throw new InvalidCredentialsException();
        }

        ResponseEntity.ok().build();
    }
}

