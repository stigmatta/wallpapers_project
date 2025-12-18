package com.odintsov.wallpapers_project.unitTests;

import com.odintsov.wallpapers_project.application.exceptions.*;
import com.odintsov.wallpapers_project.application.usecases.RegisterUser.RegisterUserCommand;
import com.odintsov.wallpapers_project.application.usecases.RegisterUser.RegisterUserUseCaseImpl;
import com.odintsov.wallpapers_project.application.mappers.UserMapper;
import com.odintsov.wallpapers_project.domain.entities.User;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private RegisterUserUseCaseImpl registerUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_validCommand_savesUser() {
        RegisterUserCommand command = new RegisterUserCommand(
                "user@example.com", "username", "1234567890", "password"
        );

        when(userRepository.findByUsername(command.username())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(command.email())).thenReturn(Optional.empty());
        when(userRepository.findByPhoneNumber(command.phoneNumber())).thenReturn(Optional.empty());

        User user = new User();
        when(mapper.toEntity(command)).thenReturn(user);

        assertDoesNotThrow(() -> registerUserUseCase.execute(command));

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void execute_emptyFields_throwsSomeFieldsAreEmpty() {
        RegisterUserCommand command = new RegisterUserCommand("", "", "123", "pass");

        assertThrows(SomeFieldsAreEmpty.class, () -> registerUserUseCase.execute(command));
    }

    @Test
    void execute_usernameTaken_throwsUsernameAlreadyTakenException() {
        RegisterUserCommand command = new RegisterUserCommand(
                "user@example.com", "username", "123", "pass"
        );
        when(userRepository.findByUsername(command.username())).thenReturn(Optional.of(new User()));

        assertThrows(UsernameAlreadyTakenException.class, () -> registerUserUseCase.execute(command));
    }

    @Test
    void execute_emailTaken_throwsEmailAlreadyTakenException() {
        RegisterUserCommand command = new RegisterUserCommand(
                "user@example.com", "username", "123", "pass"
        );
        when(userRepository.findByUsername(command.username())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(command.email())).thenReturn(Optional.of(new User()));

        assertThrows(EmailAlreadyTakenException.class, () -> registerUserUseCase.execute(command));
    }

    @Test
    void execute_phoneTaken_throwsPhoneNumberAlreadyTakenException() {
        RegisterUserCommand command = new RegisterUserCommand(
                "user@example.com", "username", "1234567890", "pass"
        );
        when(userRepository.findByUsername(command.username())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(command.email())).thenReturn(Optional.empty());
        when(userRepository.findByPhoneNumber(command.phoneNumber())).thenReturn(Optional.of(new User()));

        assertThrows(PhoneNumberAlreadyTakenException.class, () -> registerUserUseCase.execute(command));
    }
}
