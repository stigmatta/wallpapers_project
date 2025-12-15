package com.odintsov.wallpapers_project.application.dtos.Printing;

import com.odintsov.wallpapers_project.application.dtos.common.BaseProduct.BaseProductListResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public class PrintingListResponse extends BaseProductListResponse {
}