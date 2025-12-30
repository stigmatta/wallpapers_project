package com.odintsov.wallpapers_project;

import com.odintsov.wallpapers_project.initializers.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

    private final WallpaperInitializer wallpaperInitializer;
    private final SouvenirInitializer souvenirInitializer;
    private final CategoryInitializer categoryInitializer;
    private final PrintingInitializer printingInitializer;
    private final UserInitializer userInitializer;


    @Override
    public void run(String... args) {
        try {
            categoryInitializer.initCategories();
            wallpaperInitializer.initWallpapers();
            printingInitializer.initPrintings();
            userInitializer.initUsers();
            souvenirInitializer.initSouvenirs();
            log.info("Data initialization completed successfully.");
        } catch (Exception e) {
            log.error("Failed to initialize data", e);
        }
    }

}
