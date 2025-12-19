package com.odintsov.wallpapers_project;

import com.odintsov.wallpapers_project.initializers.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final WallpaperInitializer wallpaperInitializer;
    private final SouvenirInitializer souvenirInitializer;
    private final CategoryInitializer categoryInitializer;
    private final PrintingInitializer printingInitializer;
    private final UserInitializer userInitializer;


    @Override
    public void run(String... args) {
        categoryInitializer.initCategories();
        wallpaperInitializer.initWallpapers();
        printingInitializer.initPrintings();
        userInitializer.initUsers();
        souvenirInitializer.initSouvenirs();
    }
}
