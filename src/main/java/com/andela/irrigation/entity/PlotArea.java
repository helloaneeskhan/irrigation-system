package com.andela.irrigation.entity;

import com.andela.irrigation.dto.PlotAreaDto;
import com.andela.irrigation.enums.AreaUnit;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PlotArea {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private BigDecimal area;

  @Column private AreaUnit unit;

  public static PlotArea fromDto(PlotAreaDto plotAreaDto) {
    return PlotArea.builder()
        .id(plotAreaDto.getId())
        .area(plotAreaDto.getArea())
        .unit(plotAreaDto.getUnit())
        .build();
  }
}
