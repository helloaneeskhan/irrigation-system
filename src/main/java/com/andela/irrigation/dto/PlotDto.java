package com.andela.irrigation.dto;

import com.andela.irrigation.entity.Plot;
import java.time.LocalDateTime;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PlotDto {

  private Long id;

  @Size(min = 3, max = 10)
  @NotNull
  private String plotCode;

  @Size(max = 200)
  private String description;

  private LocalDateTime registrationDate;

  @Valid private PlotAreaDto plotArea;

  public static PlotDto fromEntity(Plot plot) {
    return PlotDto.builder()
        .id(plot.getId())
        .plotCode(plot.getPlotCode())
        .description(plot.getDescription())
        .registrationDate(plot.getRegistrationDate())
        .plotArea(PlotAreaDto.fromEntity(plot.getPlotArea()))
        .build();
  }
}
