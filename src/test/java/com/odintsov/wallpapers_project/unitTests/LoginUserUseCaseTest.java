package com.odintsov.wallpapers_project.unitTests;

import com.odintsov.wallpapers_project.application.exceptions.InvalidCredentialsException;
import com.odintsov.wallpapers_project.application.services.SessionService;
import com.odintsov.wallpapers_project.application.usecases.LoginUser.LoginUserCommand;
import com.odintsov.wallpapers_project.application.usecases.LoginUser.LoginUserUseCaseImpl;
import com.odintsov.wallpapers_project.application.usecases.LoginUser.LoginUserValidator;
import com.odintsov.wallpapers_project.domain.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

public class LoginUserUseCaseTest {

    @Mock
    private LoginUserValidator validator;

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private LoginUserUseCaseImpl loginUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_validCredentials_returnsSessionUuid() {
        String rawUserId = UUID.randomUUID().toString();
        User user = User.builder()
                .id(rawUserId)
                .email("test@test.com")
                .build();

        LoginUserCommand command = new LoginUserCommand("test@test.com", "password123");
        String expectedSessionId = UUID.randomUUID().toString();

        when(validator.validate(any(LoginUserCommand.class)))
                .thenReturn(user);

        when(sessionService.createSession(eq(UUID.fromString(rawUserId)), anyLong()))
                .thenReturn(expectedSessionId);

        UUID result = loginUserUseCase.execute(command);

        assertNotNull(result);
        assertEquals(UUID.fromString(expectedSessionId), result);
    }

    @Test
    void execute_validatorThrowsException_propagatesException() {
        LoginUserCommand command = new LoginUserCommand("bad", "bad");

        when(validator.validate(any())).thenThrow(new InvalidCredentialsException());

        assertThrows(InvalidCredentialsException.class, () -> loginUserUseCase.execute(command));

        verifyNoInteractions(sessionService);
    }
}
