package com.odintsov.wallpapers_project.application.dtos.Wallpaper;

import com.odintsov.wallpapers_project.application.dtos.common.BaseProduct.BaseProductFilter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class WallpaperFilter extends BaseProductFilter {
}
