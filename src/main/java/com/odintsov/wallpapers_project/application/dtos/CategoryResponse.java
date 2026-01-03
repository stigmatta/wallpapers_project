package com.odintsov.wallpapers_project.application.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {
    private String id;
    private String name;
}
