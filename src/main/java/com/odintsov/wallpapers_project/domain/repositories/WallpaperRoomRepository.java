package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.domain.entities.WallpaperRoom;

import java.util.List;

public interface WallpaperRoomRepository {
    long count();

    List<WallpaperRoom> findAll();

    List<WallpaperRoom> saveAll(List<WallpaperRoom> rooms);
}