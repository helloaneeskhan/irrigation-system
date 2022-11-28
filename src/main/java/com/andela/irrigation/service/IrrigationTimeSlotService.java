package com.andela.irrigation.service;

import com.andela.irrigation.dto.IrrigationTimeSlotDto;
import com.andela.irrigation.dto.IrrigationTimeSlotRequestDto;
import org.springframework.data.domain.Page;

public interface IrrigationTimeSlotService {
  IrrigationTimeSlotDto getIrrigationTimeSlotById(Long id);

  Page<IrrigationTimeSlotDto> getAllIrrigationTimeSlotByPlotId(Long plotId, int page, int size);

  IrrigationTimeSlotDto saveIrrigationTimeSlotDto(
      IrrigationTimeSlotRequestDto irrigationTimeSlotRequestDto);

  IrrigationTimeSlotDto updateIrrigationTimeSlotDto(IrrigationTimeSlotDto irrigationTimeSlotDto);
}
