package com.odintsov.wallpapers_project.infrastructure.adapters.jpa;

import com.odintsov.wallpapers_project.domain.entities.PrintMethod;
import com.odintsov.wallpapers_project.domain.repositories.PrintingMethodRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.jpa.JpaPrintingMethodRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("jpa")
public class JpaPrintingMethodRepositoryAdapter implements PrintingMethodRepository {

    private final JpaPrintingMethodRepository jpaRepository;

    public JpaPrintingMethodRepositoryAdapter(JpaPrintingMethodRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<PrintMethod> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<PrintMethod> saveAll(List<PrintMethod> entities) {
        return jpaRepository.saveAll(entities);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }
}
