package com.odintsov.wallpapers_project.application.dtos.Souvenir;

import com.odintsov.wallpapers_project.application.dtos.common.BaseProduct.BaseProductDetailedResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public class SouvenirDetailedResponse extends BaseProductDetailedResponse {
    private Float width;
    private Float length;
    private Float thickness;
}
