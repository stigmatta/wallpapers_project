package com.odintsov.wallpapers_project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Web-layer configuration for the application.
 * <p>
 * This class configures global CORS (Cross-Origin Resource Sharing) rules,
 * enabling the frontend application (typically running on React/Next.js)
 * to securely interact with the REST API.
 * </p>
 */
@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                "http://localhost:3000",
                                "https://react-wallpaper-java-git-verce-7c3143-andrey-odintsovs-projects.vercel.app",
                                "https://react-wallpaper-java-drab.vercel.app"
                        )                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}