package com.odintsov.wallpapers_project.application.usecases.LoginUser;

import com.odintsov.wallpapers_project.application.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginUserUseCaseImpl implements LoginUserUseCase {

    private final LoginUserValidator validator;
    private final SessionService sessionService;

    @Override
    public UUID execute(LoginUserCommand command) {

        var user = validator.validate(command);

        int EXPIRE_MINUTES = 60;
        return UUID.fromString(
                sessionService.createSession(UUID.fromString(user.getId()), EXPIRE_MINUTES)
        );
    }
}



