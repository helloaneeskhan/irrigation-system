package com.andela.irrigation.dto;

import com.andela.irrigation.entity.PlotArea;
import com.andela.irrigation.enums.AreaUnit;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PlotAreaDto {

  private Long id;

  @NotNull private BigDecimal area;

  @NotNull private AreaUnit unit;

  public static PlotAreaDto fromEntity(PlotArea plotArea) {
    return PlotAreaDto.builder()
        .id(plotArea.getId())
        .area(plotArea.getArea())
        .unit(plotArea.getUnit())
        .build();
  }
}
