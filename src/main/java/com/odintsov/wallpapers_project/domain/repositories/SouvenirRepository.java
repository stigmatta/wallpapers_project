package com.odintsov.wallpapers_project.domain.repositories;


import com.odintsov.wallpapers_project.application.dtos.Souvenir.SouvenirFilter;
import com.odintsov.wallpapers_project.domain.entities.Souvenir;

public interface SouvenirRepository extends CrudRepository<Souvenir, String, SouvenirFilter> {
}
