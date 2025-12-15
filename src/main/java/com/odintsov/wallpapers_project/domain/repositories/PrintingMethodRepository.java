package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.domain.entities.PrintMethod;

import java.util.List;

public interface PrintingMethodRepository {
    long count();

    List<PrintMethod> findAll();

    List<PrintMethod> saveAll(List<PrintMethod> methods);
}