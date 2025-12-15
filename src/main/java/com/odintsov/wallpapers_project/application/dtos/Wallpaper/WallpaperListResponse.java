package com.odintsov.wallpapers_project.application.dtos.Wallpaper;

import com.odintsov.wallpapers_project.application.dtos.common.BaseProduct.BaseProductListResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public class WallpaperListResponse extends BaseProductListResponse {
}