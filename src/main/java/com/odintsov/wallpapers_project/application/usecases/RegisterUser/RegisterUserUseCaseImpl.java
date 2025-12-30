package com.odintsov.wallpapers_project.application.usecases.RegisterUser;

import com.odintsov.wallpapers_project.application.mappers.UserMapper;
import com.odintsov.wallpapers_project.domain.entities.User;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final RegisterUserValidator validator;
    private final UserMapper mapper;

    @Override
    public void execute(RegisterUserCommand command) {
        validator.validate(command);

        User user = mapper.fromCommandToEntity(command);
        userRepository.save(user);
    }
}
