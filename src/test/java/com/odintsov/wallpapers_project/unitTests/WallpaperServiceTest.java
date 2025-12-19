package com.odintsov.wallpapers_project.unitTests;

import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Wallpaper.WallpaperListResponse;
import com.odintsov.wallpapers_project.application.mappers.WallpaperMapper;
import com.odintsov.wallpapers_project.application.services.WallpaperService;
import com.odintsov.wallpapers_project.domain.entities.Wallpaper;
import com.odintsov.wallpapers_project.domain.repositories.WallpaperRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WallpaperServiceTest {

    @Mock
    private WallpaperRepository repository;

    @Mock
    private WallpaperMapper mapper;

    @InjectMocks
    private WallpaperService service;

    private Wallpaper wallpaper1;
    private Wallpaper wallpaper2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        wallpaper1 = new Wallpaper();
        wallpaper2 = new Wallpaper();
    }

    @Test
    void testFindAll_returnsListResponses() {
        List<Wallpaper> wallpapers = List.of(wallpaper1, wallpaper2);
        when(repository.findAll()).thenReturn(wallpapers);

        when(mapper.toListResponseDto(any(Wallpaper.class)))
                .thenAnswer(invocation -> new WallpaperListResponse());

        List<WallpaperListResponse> result = service.findAll();

        assertEquals(2, result.size());

        verify(mapper, times(2)).toListResponseDto(any(Wallpaper.class));

        verify(repository, times(1)).findAll();

    }

    @Test
    void testFindById_returnsDetailedResponse() {
        String wallpaperId = "test-uuid-123";

        Wallpaper wallpaper = new Wallpaper();
        wallpaper.setId(wallpaperId);

        WallpaperDetailedResponse response = new WallpaperDetailedResponse();

        when(repository.findById(wallpaperId)).thenReturn(Optional.of(wallpaper));
        when(mapper.toDetailedResponseDto(wallpaper)).thenReturn(response);

        WallpaperDetailedResponse result = service.findById(wallpaperId);

        assertNotNull(result);
        verify(repository, times(1)).findById(wallpaperId);
        verify(mapper, times(1)).toDetailedResponseDto(wallpaper);
    }

    @Test
    void testSave_returnsDetailedResponse() {
        Wallpaper wallpaper = new Wallpaper();
        Wallpaper savedWallpaper = new Wallpaper();
        WallpaperDetailedResponse response = new WallpaperDetailedResponse();

        when(repository.save(wallpaper)).thenReturn(savedWallpaper);
        when(mapper.toDetailedResponseDto(savedWallpaper)).thenReturn(response);

        WallpaperDetailedResponse result = service.save(wallpaper);

        assertNotNull(result);
        verify(repository).save(wallpaper);
        verify(mapper).toDetailedResponseDto(savedWallpaper);
    }
}
