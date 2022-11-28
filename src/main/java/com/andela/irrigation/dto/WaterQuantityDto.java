package com.andela.irrigation.dto;

import com.andela.irrigation.entity.WaterQuantity;
import com.andela.irrigation.enums.WaterUnit;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class WaterQuantityDto {
  private Long id;
  private BigDecimal quantity;
  private WaterUnit waterUnit;

  public static WaterQuantityDto fromEntity(WaterQuantity waterQuantity) {
    return WaterQuantityDto.builder()
        .id(waterQuantity.getId())
        .quantity(waterQuantity.getQuantity())
        .waterUnit(waterQuantity.getWaterUnit())
        .build();
  }
}
