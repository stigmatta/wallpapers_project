package com.odintsov.wallpapers_project.application.usecases.RegisterUser;

import com.odintsov.wallpapers_project.application.exceptions.EmailAlreadyTakenException;
import com.odintsov.wallpapers_project.application.exceptions.PhoneNumberAlreadyTakenException;
import com.odintsov.wallpapers_project.application.exceptions.SomeFieldsAreEmpty;
import com.odintsov.wallpapers_project.application.exceptions.UsernameAlreadyTakenException;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserValidator {

    private final UserRepository userRepository;

    public void validate(RegisterUserCommand command) {

        if (command.email() == null || command.username() == null
                || command.email().isBlank() || command.username().isBlank()) {
            throw new SomeFieldsAreEmpty();
        }

        if (userRepository.findByUsername(command.username()).isPresent()) {
            throw new UsernameAlreadyTakenException(command.username());
        }

        if (userRepository.findByEmail(command.email()).isPresent()) {
            throw new EmailAlreadyTakenException(command.email());
        }

        if (command.phoneNumber() != null &&
                userRepository.findByPhoneNumber(command.phoneNumber()).isPresent()) {
            throw new PhoneNumberAlreadyTakenException(command.phoneNumber());
        }
    }
}

