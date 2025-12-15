package com.odintsov.wallpapers_project.infrastructure.adapters;

import com.odintsov.wallpapers_project.domain.entities.PrintMethod;
import com.odintsov.wallpapers_project.domain.repositories.PrintingMethodRepository;
import com.odintsov.wallpapers_project.infrastructure.persistence.JpaPrintingMethodRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrintingMethodRepositoryAdapter implements PrintingMethodRepository {

    private final JpaPrintingMethodRepository jpaRepository;

    public PrintingMethodRepositoryAdapter(JpaPrintingMethodRepository jpaRepository) {
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
