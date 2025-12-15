package com.odintsov.wallpapers_project.application.usecases.RegisterUser;

public record RegisterUserCommand(
        String username,
        String email,
        String phoneNumber,
        String password
) {
}

