package com.odintsov.wallpapers_project.unitTests;

import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingListResponse;
import com.odintsov.wallpapers_project.application.mappers.PrintingMapper;
import com.odintsov.wallpapers_project.application.services.PrintingService;
import com.odintsov.wallpapers_project.domain.entities.Printing;
import com.odintsov.wallpapers_project.domain.repositories.PrintingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PrintingServiceTest {

    @Mock
    private PrintingRepository repository;

    @Mock
    private PrintingMapper mapper;

    @InjectMocks
    private PrintingService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll_returnsListResponses() {
        Printing printing = new Printing();
        List<Printing> printings = List.of(printing);
        PrintingListResponse response = new PrintingListResponse();

        when(repository.findAll()).thenReturn(printings);
        when(mapper.toListResponseDto(printing)).thenReturn(response);

        List<PrintingListResponse> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(response, result.getFirst());

        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).toListResponseDto(printing);
    }

}
