package com.odintsov.wallpapers_project.presentation.controllers;


import com.odintsov.wallpapers_project.application.usecases.LoginUser.LoginUserCommand;
import com.odintsov.wallpapers_project.application.usecases.LoginUser.LoginUserUseCase;
import com.odintsov.wallpapers_project.application.usecases.RegisterUser.RegisterUserCommand;
import com.odintsov.wallpapers_project.application.usecases.RegisterUser.RegisterUserUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final LoginUserUseCase loginUseCase;
    private final RegisterUserUseCase registerUserUseCase;

    public UserController(LoginUserUseCase loginUseCase, RegisterUserUseCase registerUserUseCase) {
        this.loginUseCase = loginUseCase;
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginUserCommand command) {
        loginUseCase.execute(command);
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterUserCommand command) {
        registerUserUseCase.execute(command);
    }

}
