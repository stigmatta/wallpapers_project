package com.odintsov.wallpapers_project.application.usecases.GlobalSearch;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GlobalSearchResponse {
    List<SearchItemResponse> items;
}
