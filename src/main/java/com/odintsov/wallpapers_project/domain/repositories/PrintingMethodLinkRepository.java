package com.odintsov.wallpapers_project.domain.repositories;


import com.odintsov.wallpapers_project.domain.entities.PrintingMethodsLink;

import java.util.List;

public interface PrintingMethodLinkRepository {
    List<PrintingMethodsLink> saveAll(List<PrintingMethodsLink> printings);
}
