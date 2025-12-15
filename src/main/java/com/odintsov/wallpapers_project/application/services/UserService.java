package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.dtos.User.UserDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.User.UserFilter;
import com.odintsov.wallpapers_project.application.dtos.User.UserListResponse;
import com.odintsov.wallpapers_project.application.mappers.UserMapper;
import com.odintsov.wallpapers_project.domain.entities.User;
import com.odintsov.wallpapers_project.domain.repositories.UserRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseCrudService<
        User,
        Long,
        UserFilter,
        UserListResponse,
        UserDetailedResponse,
        UserRepository
        > {
    protected UserService(UserRepository repository,
                          UserMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected Specification<User> buildSpecification(UserFilter filter) {
        if (filter == null) {
            return Specification.unrestricted();
        }

        Specification<User> spec = Specification.unrestricted();

        if (filter.getUsername() != null && !filter.getUsername().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("username")), "%" + filter.getUsername().toLowerCase() + "%"));
        }

        if (filter.getEmail() != null && !filter.getEmail().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("email")), "%" + filter.getEmail().toLowerCase() + "%"));
        }

        return spec;
    }
}
