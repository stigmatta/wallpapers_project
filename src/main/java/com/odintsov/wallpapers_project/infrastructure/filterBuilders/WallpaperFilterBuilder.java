package com.odintsov.wallpapers_project.infrastructure.filterBuilders;

import com.google.cloud.firestore.Query;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperFilter;

public class WallpaperFilterBuilder extends BaseProductFirestoreFilterBuilder<WallpaperFilter> {

    @Override
    public Query apply(Query baseQuery, WallpaperFilter filter) {
        return super.apply(baseQuery, filter);
    }
}
