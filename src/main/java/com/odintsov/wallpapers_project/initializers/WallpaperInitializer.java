package com.odintsov.wallpapers_project.initializers;

import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import com.odintsov.wallpapers_project.domain.entities.WallpaperMaterial;
import com.odintsov.wallpapers_project.domain.entities.WallpaperRoom;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperMaterialRepository;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRepository;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@RequiredArgsConstructor
public class WallpaperInitializer {

    private final WallpaperRepository wallpaperRepository;
    private final WallpaperMaterialRepository wallpaperMaterialRepository;
    private final WallpaperRoomRepository wallpaperRoomRepository;

    @Transactional
    public void initWallpapers() {
        if (wallpaperRepository.count() > 0) {
            return;
        }

        // Create and save materials first
        List<WallpaperMaterial> materials = createMaterials();

        // Create and save rooms first
        List<WallpaperRoom> rooms = createRooms();

        // Now create wallpapers with saved materials and rooms
        List<Wallpaper> wallpapers = createWallpapers(materials, rooms);

        wallpaperRepository.saveAll(wallpapers);
    }

    private List<WallpaperMaterial> createMaterials() {
        if (wallpaperMaterialRepository.count() > 0) {
            return wallpaperMaterialRepository.findAll();
        }

        WallpaperMaterial mat1 = WallpaperMaterial.builder()
                .id(UUID.randomUUID().toString())
                .name("САМОКЛЕЮЧІ")
                .priceMultiplier(1.0)
                .build();

        WallpaperMaterial mat2 = WallpaperMaterial.builder()
                .id(UUID.randomUUID().toString())
                .name("ФЛІЗЕЛІН")
                .priceMultiplier(1.2)
                .build();

        WallpaperMaterial mat3 = WallpaperMaterial.builder()
                .id(UUID.randomUUID().toString())
                .name("ВІНІЛ НА ФЛІЗЕЛІНІ")
                .priceMultiplier(1.5)
                .build();

        return wallpaperMaterialRepository.saveAll(Arrays.asList(mat1, mat2, mat3));
    }

    private List<WallpaperRoom> createRooms() {
        if (wallpaperRoomRepository.count() > 0) {
            return wallpaperRoomRepository.findAll();
        }

        WallpaperRoom r1 = WallpaperRoom.builder()
                .id(UUID.randomUUID().toString())
                .name("ВІТАЛЬНЯ")
                .build();

        WallpaperRoom r2 = WallpaperRoom.builder()
                .id(UUID.randomUUID().toString())
                .name("СПАЛЬНЯ")
                .build();

        return wallpaperRoomRepository.saveAll(Arrays.asList(r1, r2));
    }

    private List<Wallpaper> createWallpapers(List<WallpaperMaterial> materials, List<WallpaperRoom> rooms) {
        WallpaperMaterial mat1 = materials.get(0);
        WallpaperMaterial mat2 = materials.get(1);

        WallpaperRoom r1 = rooms.get(0);
        WallpaperRoom r2 = rooms.get(1);

        Wallpaper wp1 = new Wallpaper();
        wp1.setId(UUID.randomUUID().toString());
        wp1.setName("Hexagon Pattern");
        wp1.setArticle("WP-001");
        wp1.setBasePrice(49.99f);
        wp1.setSalePrice(39.99f);
        wp1.setImage("/images/hexagon.jpg");
        wp1.setDescription("Stylish hexagon wallpaper for modern interiors");
        wp1.setDensity(120.5f);
        wp1.setWaterproof(true);
        wp1.setQuantity(100);
        wp1.setRooms(new ArrayList<>(new HashSet<>(Arrays.asList(r1, r2))));
        wp1.setMaterials(new ArrayList<>(new HashSet<>(Arrays.asList(mat1, mat2))));

        Wallpaper wp2 = new Wallpaper();
        wp2.setId(UUID.randomUUID().toString());
        wp2.setName("Floral Vintage");
        wp2.setArticle("WP-002");
        wp2.setBasePrice(59.99f);
        wp2.setSalePrice(49.99f);
        wp2.setImage("/images/floral.jpg");
        wp2.setDescription("Elegant vintage floral wallpaper");
        wp2.setDensity(110.0f);
        wp2.setWaterproof(false);
        wp2.setQuantity(80);
        wp2.setRooms(new ArrayList<>(new HashSet<>(Arrays.asList(r1, r2))));
        wp2.setMaterials(new ArrayList<>(new HashSet<>(List.of(mat2))));

        return Arrays.asList(wp1, wp2);
    }
}
