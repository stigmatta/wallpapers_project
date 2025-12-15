package com.odintsov.wallpapers_project.infrastructure.adapters;

import com.odintsov.wallpapers_project.domain.entities.Souvenir;
import com.odintsov.wallpapers_project.domain.repositories.SouvenirRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.JpaSouvenirRepository;
import org.springframework.stereotype.Component;

@Component
public class SouvenirRepositoryAdapter
        extends BaseJpaRepositoryAdapter<Souvenir, Long, JpaSouvenirRepository>
        implements SouvenirRepository {

    public SouvenirRepositoryAdapter(JpaSouvenirRepository jpaRepository) {
        super(jpaRepository);
    }
}