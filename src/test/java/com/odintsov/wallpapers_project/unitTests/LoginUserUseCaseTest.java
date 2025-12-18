package com.odintsov.wallpapers_project.unitTests;

import com.odintsov.wallpapers_project.application.exceptions.InvalidCredentialsException;
import com.odintsov.wallpapers_project.application.usecases.LoginUser.LoginUserCommand;
import com.odintsov.wallpapers_project.application.usecases.LoginUser.LoginUserUseCaseImpl;
import com.odintsov.wallpapers_project.domain.entities.User;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LoginUserUseCaseTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private LoginUserUseCaseImpl loginUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_validCredentials_noExceptionThrown() {
        User user = new User();
        user.setHashedPassword("hashedPassword");

        LoginUserCommand command = new LoginUserCommand("user@example.com", "password");

        when(userRepository.findByEmailOrUsername(command.usernameOrEmail()))
                .thenReturn(Optional.of(user));
        when(passwordEncoder.matches(command.password(), user.getHashedPassword()))
                .thenReturn(true);

        assertDoesNotThrow(() -> loginUserUseCase.execute(command));

        verify(userRepository, times(1)).findByEmailOrUsername(command.usernameOrEmail());
        verify(passwordEncoder, times(1)).matches(command.password(), user.getHashedPassword());
    }

    @Test
    void execute_userNotFound_throwsInvalidCredentialsException() {
        LoginUserCommand command = new LoginUserCommand("user@example.com", "password");

        when(userRepository.findByEmailOrUsername(command.usernameOrEmail()))
                .thenReturn(Optional.empty());

        assertThrows(InvalidCredentialsException.class, () -> loginUserUseCase.execute(command));
    }

    @Test
    void execute_invalidPassword_throwsInvalidCredentialsException() {
        User user = new User();
        user.setHashedPassword("hashedPassword");

        LoginUserCommand command = new LoginUserCommand("user@example.com", "wrongPassword");

        when(userRepository.findByEmailOrUsername(command.usernameOrEmail()))
                .thenReturn(Optional.of(user));
        when(passwordEncoder.matches(command.password(), user.getHashedPassword()))
                .thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> loginUserUseCase.execute(command));
    }
}
