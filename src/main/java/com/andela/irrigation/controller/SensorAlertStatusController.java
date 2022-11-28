package com.andela.irrigation.controller;

import com.andela.irrigation.constants.AppConstants;
import com.andela.irrigation.dto.SensorAlertStatusDto;
import com.andela.irrigation.service.SensorAlertStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.API_VERSION_1 + "/sensor-alert")
@RequiredArgsConstructor
@Slf4j
public class SensorAlertStatusController {

  @Autowired private SensorAlertStatusService sensorAlertStatusService;

  @GetMapping
  public SensorAlertStatusDto getSensorAlert(@RequestParam("id") Long id) {
    return sensorAlertStatusService.getSensorAlertStatusById(id);
  }

  @GetMapping("/list")
  public Page<SensorAlertStatusDto> getSensorAlertList(
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "10") int size) {
    return sensorAlertStatusService.getAllSensorAlertStatusList(page, size);
  }
}
