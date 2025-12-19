package com.odintsov.wallpapers_project.application.services;

import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirDetailedResponse;
import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirFilter;
import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirListResponse;
import com.odintsov.wallpapers_project.application.mappers.SouvenirMapper;
import com.odintsov.wallpapers_project.domain.entities.Souvenir;
import com.odintsov.wallpapers_project.domain.repositories.SouvenirRepository;
import org.springframework.stereotype.Service;

@Service
public class SouvenirService extends BaseCrudService<
        Souvenir,
        String,
        SouvenirFilter,
        SouvenirListResponse,
        SouvenirDetailedResponse,
        SouvenirRepository
        > {

    public SouvenirService(SouvenirRepository repository,
                           SouvenirMapper mapper) {
        super(repository, mapper);
    }

}

