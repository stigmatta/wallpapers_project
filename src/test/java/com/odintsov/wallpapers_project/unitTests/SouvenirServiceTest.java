package com.odintsov.wallpapers_project.unitTests;

import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirListResponse;
import com.odintsov.wallpapers_project.application.mappers.SouvenirMapper;
import com.odintsov.wallpapers_project.application.services.SouvenirService;
import com.odintsov.wallpapers_project.domain.entities.Souvenir;
import com.odintsov.wallpapers_project.domain.repositories.SouvenirRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SouvenirServiceTest {

    @Mock
    private SouvenirRepository repository;

    @Mock
    private SouvenirMapper mapper;

    @InjectMocks
    private SouvenirService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll_returnsListResponses() {
        Souvenir souvenir = new Souvenir();
        List<Souvenir> souvenirs = List.of(souvenir);
        SouvenirListResponse response = new SouvenirListResponse();

        when(repository.findAll()).thenReturn(souvenirs);
        when(mapper.toListResponseDto(souvenir)).thenReturn(response);

        List<SouvenirListResponse> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(response, result.getFirst());

        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).toListResponseDto(souvenir);
    }

    @Test
    void testFindById_entityExists_returnsDetailedResponse() {
        Souvenir souvenir = new Souvenir();
        when(repository.findById(1L)).thenReturn(Optional.of(souvenir));

        service.findById(1L);

        verify(repository, times(1)).findById(1L);
        verify(mapper, times(1)).toDetailedResponseDto(souvenir);
    }
}
