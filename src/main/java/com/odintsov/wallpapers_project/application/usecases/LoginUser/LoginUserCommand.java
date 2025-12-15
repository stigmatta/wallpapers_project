package com.odintsov.wallpapers_project.application.usecases.LoginUser;

public record LoginUserCommand(
        String usernameOrEmail,
        String password
) {
}

