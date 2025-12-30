package com.odintsov.wallpapers_project.presentation.controllers;


import com.odintsov.wallpapers_project.application.dtos.User.UserDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.User.UserFilter;
import com.odintsov.wallpapers_project.application.dtos.User.UserListResponse;
import com.odintsov.wallpapers_project.application.services.SessionService;
import com.odintsov.wallpapers_project.application.services.UserService;
import com.odintsov.wallpapers_project.application.usecases.LoginUser.LoginUserCommand;
import com.odintsov.wallpapers_project.application.usecases.LoginUser.LoginUserUseCase;
import com.odintsov.wallpapers_project.application.usecases.LogoutUser.LogoutUserUseCase;
import com.odintsov.wallpapers_project.application.usecases.RegisterUser.RegisterUserCommand;
import com.odintsov.wallpapers_project.application.usecases.RegisterUser.RegisterUserUseCase;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * REST Controller for user identity and access management.
 * <p>
 * This controller handles account lifecycles (registration, login, logout)
 * and provides endpoints for user profile management and administrative
 * listing with complex filtering support.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final LoginUserUseCase loginUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final LogoutUserUseCase logoutUserUseCase;
    private final UserService userService;
    private final SessionService sessionService;


    public UserController(LoginUserUseCase loginUseCase, RegisterUserUseCase registerUserUseCase, LogoutUserUseCase logoutUserUseCase, UserService userService,
                          SessionService sessionService) {
        this.loginUseCase = loginUseCase;
        this.registerUserUseCase = registerUserUseCase;
        this.logoutUserUseCase = logoutUserUseCase;
        this.userService = userService;
        this.sessionService = sessionService;
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
    public UUID login(@Valid @RequestBody LoginUserCommand command) {
        return loginUseCase.execute(command);
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterUserCommand command) {
        registerUserUseCase.execute(command);
    }


    @GetMapping("/profile")
    public UserDetailedResponse getProfile(@RequestHeader("Authorization") String authHeader) {
        UUID userId = sessionService.getUserIdByAuthHeader(authHeader);
        return userService.findById(userId.toString());
    }

    @GetMapping("/logout")
    public void logout(@RequestHeader("Authorization") String authHeader) {
        logoutUserUseCase.execute(authHeader);
    }

}
