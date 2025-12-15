package com.odintsov.wallpapers_project;

import com.odintsov.wallpapers_project.initializers.PrintingInitializer;
import com.odintsov.wallpapers_project.initializers.SouvenirInitializer;
import com.odintsov.wallpapers_project.initializers.UserInitializer;
import com.odintsov.wallpapers_project.initializers.WallpaperInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final WallpaperInitializer wallpaperInitializer;
    private final SouvenirInitializer souvenirInitializer;
    private final PrintingInitializer printingInitializer;
    private final UserInitializer userInitializer;


    @Override
    public void run(String... args) {
        wallpaperInitializer.initWallpapers();
        souvenirInitializer.initSouvenirs();
        printingInitializer.initPrintings();
        userInitializer.initUsers();
    }
}
