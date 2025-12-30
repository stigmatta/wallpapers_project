package com.odintsov.wallpapers_project.application.usecases.LogoutUser;

public interface LogoutUserUseCase {
    void execute(String authHeader);
}
