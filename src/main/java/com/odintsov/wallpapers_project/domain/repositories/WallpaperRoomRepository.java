package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.domain.entities.WallpaperRoom;
import org.springframework.stereotype.Repository;

@Repository
public interface WallpaperRoomRepository extends BaseRepository<WallpaperRoom, Long> {}