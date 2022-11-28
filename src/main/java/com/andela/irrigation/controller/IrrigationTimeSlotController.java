package com.andela.irrigation.controller;

import com.andela.irrigation.constants.AppConstants;
import com.andela.irrigation.dto.IrrigationTimeSlotDto;
import com.andela.irrigation.dto.IrrigationTimeSlotRequestDto;
import com.andela.irrigation.service.IrrigationTimeSlotService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.API_VERSION_1 + "/time-slot")
@RequiredArgsConstructor
@Slf4j
public class IrrigationTimeSlotController {

  @Autowired private IrrigationTimeSlotService irrigationTimeSlotService;

  @GetMapping
  public IrrigationTimeSlotDto getIrrigationTimeSlot(@RequestParam("id") Long id) {
    return irrigationTimeSlotService.getIrrigationTimeSlotById(id);
  }

  @GetMapping("/list")
  public Page<IrrigationTimeSlotDto> getIrrigationTimeSlotForPlotId(
      @RequestParam Long plotId,
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "10") int size) {
    return irrigationTimeSlotService.getAllIrrigationTimeSlotByPlotId(plotId, page, size);
  }

  @PostMapping("/add")
  public IrrigationTimeSlotDto addIrrigationTimeSlot(
      @RequestBody @Valid IrrigationTimeSlotRequestDto irrigationTimeSlotRequestDto) {
    return irrigationTimeSlotService.saveIrrigationTimeSlotDto(irrigationTimeSlotRequestDto);
  }

  @PutMapping("/update")
  public IrrigationTimeSlotDto updateIrrigationTimeSlot(
      @RequestBody @Valid IrrigationTimeSlotDto irrigationTimeSlotDto) {
    return irrigationTimeSlotService.updateIrrigationTimeSlotDto(irrigationTimeSlotDto);
  }
}
