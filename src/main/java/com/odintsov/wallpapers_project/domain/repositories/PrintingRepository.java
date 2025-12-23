package com.odintsov.wallpapers_project.domain.repositories;


import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingFilter;
import com.odintsov.wallpapers_project.domain.entities.Printing;


public interface PrintingRepository extends CrudRepository<Printing, String, PrintingFilter>, SlugRepository<Printing> {
}
