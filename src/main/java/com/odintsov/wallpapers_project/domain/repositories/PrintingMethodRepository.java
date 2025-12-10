package com.odintsov.wallpapers_project.domain.repositories;

import com.odintsov.wallpapers_project.domain.entities.PrintMethod;
import org.springframework.stereotype.Repository;

@Repository
public interface PrintingMethodRepository extends BaseRepository<PrintMethod, Long> {}