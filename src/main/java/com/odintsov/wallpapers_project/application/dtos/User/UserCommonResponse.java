package com.odintsov.wallpapers_project.application.dtos.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserCommonResponse {
    private Long id;
    private String username;
    private String email;
}

