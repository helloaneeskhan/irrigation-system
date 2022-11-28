package com.andela.irrigation.entity;

import com.andela.irrigation.dto.WaterQuantityDto;
import com.andela.irrigation.enums.WaterUnit;
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
@RequiredArgsConstructor
@AllArgsConstructor
public class WaterQuantity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private BigDecimal quantity;

  @Column private WaterUnit waterUnit;

  public static WaterQuantity fromDto(WaterQuantityDto waterQuantityDto) {
    return WaterQuantity.builder()
        .id(waterQuantityDto.getId())
        .quantity(waterQuantityDto.getQuantity())
        .waterUnit(waterQuantityDto.getWaterUnit())
        .build();
  }
}
