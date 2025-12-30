package com.odintsov.wallpapers_project.application.usecases.LoginUser;

import java.util.UUID;

public interface LoginUserUseCase {
    UUID execute(LoginUserCommand command);
}
