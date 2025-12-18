package com.odintsov.wallpapers_project.application.usecases.RegisterUser;

import com.odintsov.wallpapers_project.application.exceptions.EmailAlreadyTakenException;
import com.odintsov.wallpapers_project.application.exceptions.PhoneNumberAlreadyTakenException;
import com.odintsov.wallpapers_project.application.exceptions.SomeFieldsAreEmpty;
import com.odintsov.wallpapers_project.application.exceptions.UsernameAlreadyTakenException;
import com.odintsov.wallpapers_project.application.mappers.UserMapper;
import com.odintsov.wallpapers_project.domain.entities.User;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public void execute(RegisterUserCommand command) {
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

        if (userRepository.findByPhoneNumber(command.phoneNumber()).isPresent()) {
            throw new PhoneNumberAlreadyTakenException(command.phoneNumber());
        }

        User user = mapper.toEntity(command);

        userRepository.save(user);

        ResponseEntity.ok().build();
    }
}