package com.odintsov.wallpapers_project.application.usecases.LogoutUser;

import com.odintsov.wallpapers_project.application.services.SessionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogoutUserUserCaseImpl implements LogoutUserUseCase {

    private final SessionService sessionService;

    public LogoutUserUserCaseImpl(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void execute(String authHeader) {
        String token = sessionService.extractTokenFromHeader(authHeader);
        sessionService.deleteSession(token);
    }
}
