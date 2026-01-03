package com.odintsov.wallpapers_project.unitTests;

import com.odintsov.wallpapers_project.application.exceptions.*;
import com.odintsov.wallpapers_project.application.mappers.UserMapper;
import com.odintsov.wallpapers_project.application.usecases.RegisterUser.RegisterUserCommand;
import com.odintsov.wallpapers_project.application.usecases.RegisterUser.RegisterUserUseCaseImpl;
import com.odintsov.wallpapers_project.application.usecases.RegisterUser.RegisterUserValidator;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegisterUserUseCaseTest {

    @InjectMocks
    private RegisterUserUseCaseImpl registerUserUseCase;

    @Mock
    private RegisterUserValidator validator;

    @Mock
    private UserMapper mapper;

    @Mock
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_validCommand_doesNotThrow() {
        RegisterUserCommand command = new RegisterUserCommand(
                "user@example.com", "username", "1234567890", "password"
        );

        doNothing().when(validator).validate(command);

        assertDoesNotThrow(() -> registerUserUseCase.execute(command));

        verify(validator, times(1)).validate(command);
    }

    @Test
    void execute_emptyFields_throwsSomeFieldsAreEmpty() {
        RegisterUserCommand command = new RegisterUserCommand("", "", "123", "pass");

        doThrow(new SomeFieldsAreEmpty()).when(validator).validate(command);

        assertThrows(SomeFieldsAreEmpty.class, () -> registerUserUseCase.execute(command));
        verify(validator, times(1)).validate(command);
    }

    @Test
    void execute_usernameTaken_throwsUsernameAlreadyTakenException() {
        RegisterUserCommand command = new RegisterUserCommand(
                "user@example.com", "username", "123", "pass"
        );

        doThrow(new UsernameAlreadyTakenException(command.username()))
                .when(validator).validate(command);

        assertThrows(UsernameAlreadyTakenException.class, () -> registerUserUseCase.execute(command));
        verify(validator, times(1)).validate(command);
    }

    @Test
    void execute_emailTaken_throwsEmailAlreadyTakenException() {
        RegisterUserCommand command = new RegisterUserCommand(
                "user@example.com", "username", "123", "pass"
        );

        doThrow(new EmailAlreadyTakenException(command.email()))
                .when(validator).validate(command);

        assertThrows(EmailAlreadyTakenException.class, () -> registerUserUseCase.execute(command));
        verify(validator, times(1)).validate(command);
    }

    @Test
    void execute_phoneTaken_throwsPhoneNumberAlreadyTakenException() {
        RegisterUserCommand command = new RegisterUserCommand(
                "user@example.com", "username", "1234567890", "pass"
        );

        doThrow(new PhoneNumberAlreadyTakenException(command.phoneNumber()))
                .when(validator).validate(command);

        assertThrows(PhoneNumberAlreadyTakenException.class, () -> registerUserUseCase.execute(command));
        verify(validator, times(1)).validate(command);
    }
}
