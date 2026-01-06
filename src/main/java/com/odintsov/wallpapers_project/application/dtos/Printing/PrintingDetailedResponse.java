package com.odintsov.wallpapers_project.application.dtos.Printing;

import com.odintsov.wallpapers_project.application.dtos.common.BaseProduct.BaseProductDetailedResponse;
import com.odintsov.wallpapers_project.domain.entities.PrintMethod;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public class PrintingDetailedResponse extends BaseProductDetailedResponse {
    List<PrintMethod> methods;
}
