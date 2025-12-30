package com.odintsov.wallpapers_project.application.mappers;

import com.odintsov.wallpapers_project.application.dtos.User.UserDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.User.UserListResponse;
import com.odintsov.wallpapers_project.application.mappers.common.FullMapper;
import com.odintsov.wallpapers_project.application.usecases.RegisterUser.RegisterUserCommand;
import com.odintsov.wallpapers_project.domain.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements FullMapper<
        User,
        UserListResponse,
        UserDetailedResponse,
        RegisterUserCommand
        > {
    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserListResponse toListResponseDto(User entity) {
        return UserListResponse.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .build();
    }

    @Override
    public UserDetailedResponse toDetailedResponseDto(User entity) {
        return UserDetailedResponse.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .build();
    }

    @Override
    public User fromCommandToEntity(RegisterUserCommand command) {
        return User.builder()
                .username(command.username())
                .email(command.email())
                .phoneNumber(command.phoneNumber())
                .password(passwordEncoder.encode(command.password()))
                .build();
    }

}
