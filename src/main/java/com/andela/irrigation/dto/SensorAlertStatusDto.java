package com.andela.irrigation.dto;

import com.andela.irrigation.entity.IrrigationTimeSlot;
import com.andela.irrigation.entity.SensorAlertStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SensorAlertStatusDto {

  private Long id;

  private Integer errorCode;

  private String errorMessage;

  private LocalDateTime lastRetry;

  private IrrigationTimeSlot irrigationTimeSlot;

  public static SensorAlertStatusDto fromEntity(SensorAlertStatus sensorAlertStatus) {
    return SensorAlertStatusDto.builder()
        .id(sensorAlertStatus.getId())
        .errorCode(sensorAlertStatus.getErrorCode())
        .errorMessage(sensorAlertStatus.getErrorMessage())
        .lastRetry(sensorAlertStatus.getLastRetry())
        .irrigationTimeSlot(sensorAlertStatus.getIrrigationTimeSlot())
        .build();
  }
}
