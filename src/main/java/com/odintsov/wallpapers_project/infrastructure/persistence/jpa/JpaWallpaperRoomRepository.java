package com.odintsov.wallpapers_project.infrastructure.persistence.jpa;

import com.odintsov.wallpapers_project.domain.entities.WallpaperRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaWallpaperRoomRepository
        extends JpaRepository<WallpaperRoom, Long>, JpaSpecificationExecutor<WallpaperRoom> {
}