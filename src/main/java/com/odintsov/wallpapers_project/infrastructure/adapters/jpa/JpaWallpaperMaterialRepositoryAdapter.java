package com.odintsov.wallpapers_project.infrastructure.adapters.jpa;

import com.odintsov.wallpapers_project.domain.entities.WallpaperMaterial;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperMaterialRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.jpa.JpaWallpaperMaterialRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("jpa")
public class JpaWallpaperMaterialRepositoryAdapter implements WallpaperMaterialRepository {

    private final JpaWallpaperMaterialRepository jpaRepository;

    public JpaWallpaperMaterialRepositoryAdapter(JpaWallpaperMaterialRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<WallpaperMaterial> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<WallpaperMaterial> saveAll(List<WallpaperMaterial> entities) {
        return jpaRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }
}
