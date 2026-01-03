package com.odintsov.wallpapers_project.application.dtos.Wallpaper;

import com.odintsov.wallpapers_project.application.dtos.common.BaseProduct.BaseProductDetailedResponse;
import com.odintsov.wallpapers_project.domain.entities.WallpaperMaterial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public class WallpaperDetailedResponse extends BaseProductDetailedResponse {
    private Float density;
    private Boolean waterproof;
    private Set<String> rooms;
    private Set<WallpaperMaterial> materials;
}

