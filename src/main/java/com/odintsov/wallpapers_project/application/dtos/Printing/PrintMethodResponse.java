package com.odintsov.wallpapers_project.application.dtos.Printing;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrintMethodResponse {
    private String id;
    private String name;
    private String description;
    private Integer deadline;
}
