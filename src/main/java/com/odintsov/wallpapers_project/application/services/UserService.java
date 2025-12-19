package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.dtos.User.UserDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.User.UserFilter;
import com.odintsov.wallpapers_project.application.dtos.User.UserListResponse;
import com.odintsov.wallpapers_project.application.mappers.UserMapper;
import com.odintsov.wallpapers_project.domain.entities.User;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseCrudService<
        User,
        String,
        UserFilter,
        UserListResponse,
        UserDetailedResponse,
        UserRepository
        > {
    protected UserService(UserRepository repository,
                          UserMapper mapper) {
        super(repository, mapper);
    }

}
