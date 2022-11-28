package com.andela.irrigation.entity;

import com.andela.irrigation.dto.IrrigationTimeSlotDto;
import com.andela.irrigation.dto.IrrigationTimeSlotRequestDto;
import com.andela.irrigation.enums.IrrigationStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
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
public class IrrigationTimeSlot {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column LocalDateTime irrigationTime;

  @Column private LocalDateTime lastAttemptAt;

  @Column private Long irrigationAttempt;

  @Column private IrrigationStatus irrigationStatus;

  @OneToOne(cascade = CascadeType.ALL)
  private WaterQuantity waterQuantity;

  @JsonBackReference
  @ManyToOne(targetEntity = Plot.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "plot_id")
  private Plot plot;

  public static IrrigationTimeSlot fromDto(IrrigationTimeSlotDto irrigationTimeSlotDto) {
    return IrrigationTimeSlot.builder()
        .id(irrigationTimeSlotDto.getId())
        .irrigationTime(irrigationTimeSlotDto.getIrrigationTime())
        .lastAttemptAt(irrigationTimeSlotDto.getLastAttemptAt())
        .irrigationStatus(irrigationTimeSlotDto.getIrrigationStatus())
        .waterQuantity(WaterQuantity.fromDto(irrigationTimeSlotDto.getWaterQuantity()))
        .build();
  }

  public static IrrigationTimeSlot fromRequestDto(
      IrrigationTimeSlotRequestDto irrigationTimeSlotRequestDto) {
    return IrrigationTimeSlot.builder()
        .id(irrigationTimeSlotRequestDto.getId())
        .irrigationTime(irrigationTimeSlotRequestDto.getIrrigationTime())
        .lastAttemptAt(irrigationTimeSlotRequestDto.getLastAttemptAt())
        .irrigationStatus(irrigationTimeSlotRequestDto.getIrrigationStatus())
        .waterQuantity(WaterQuantity.fromDto(irrigationTimeSlotRequestDto.getWaterQuantity()))
        .build();
  }
}
