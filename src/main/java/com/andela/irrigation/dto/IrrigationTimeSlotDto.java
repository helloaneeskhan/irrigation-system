package com.andela.irrigation.dto;

import com.andela.irrigation.entity.IrrigationTimeSlot;
import com.andela.irrigation.enums.IrrigationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class IrrigationTimeSlotDto {
  private Long id;

  @NotNull(message = "{notNull}")
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime irrigationTime;

  @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime lastAttemptAt;

  private IrrigationStatus irrigationStatus;
  private PlotDto plot;
  private WaterQuantityDto waterQuantity;

  public static IrrigationTimeSlotDto fromEntity(IrrigationTimeSlot irrigationTimeSlot) {
    return IrrigationTimeSlotDto.builder()
        .id(irrigationTimeSlot.getId())
        .plot(PlotDto.fromEntity(irrigationTimeSlot.getPlot()))
        .irrigationTime(irrigationTimeSlot.getIrrigationTime())
        .lastAttemptAt(irrigationTimeSlot.getLastAttemptAt())
        .irrigationStatus(irrigationTimeSlot.getIrrigationStatus())
        .waterQuantity(WaterQuantityDto.fromEntity(irrigationTimeSlot.getWaterQuantity()))
        .build();
  }
}
