package com.odintsov.wallpapers_project.infrastructure.adapters.jpa;

import com.odintsov.wallpapers_project.domain.entities.WallpaperRoom;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRoomRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.jpa.JpaWallpaperRoomRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("jpa")
public class JpaWallpaperRoomRepositoryAdapter implements WallpaperRoomRepository {

    private final JpaWallpaperRoomRepository jpaRepository;

    public JpaWallpaperRoomRepositoryAdapter(JpaWallpaperRoomRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<WallpaperRoom> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<WallpaperRoom> saveAll(List<WallpaperRoom> entities) {
        return jpaRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }
}
