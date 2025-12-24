package com.odintsov.wallpapers_project.presentation.controllers;


import com.odintsov.wallpapers_project.application.dtos.User.UserFilter;
import com.odintsov.wallpapers_project.application.dtos.User.UserListResponse;
import com.odintsov.wallpapers_project.application.services.UserService;
import com.odintsov.wallpapers_project.application.usecases.LoginUser.LoginUserCommand;
import com.odintsov.wallpapers_project.application.usecases.LoginUser.LoginUserUseCase;
import com.odintsov.wallpapers_project.application.usecases.RegisterUser.RegisterUserCommand;
import com.odintsov.wallpapers_project.application.usecases.RegisterUser.RegisterUserUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final LoginUserUseCase loginUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final UserService userService;

    public UserController(LoginUserUseCase loginUseCase, RegisterUserUseCase registerUserUseCase, UserService userService) {
        this.loginUseCase = loginUseCase;
        this.registerUserUseCase = registerUserUseCase;
        this.userService = userService;
    }

    @GetMapping
    public Page<UserListResponse> getUsers(
            @ModelAttribute UserFilter filter,
            @PageableDefault(size = 20, sort = "id",
                    direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return userService.findAll(filter, pageable);
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
