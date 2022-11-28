package com.andela.irrigation.service;

import com.andela.irrigation.dto.SensorAlertStatusDto;
import org.springframework.data.domain.Page;

public interface SensorAlertStatusService {

  SensorAlertStatusDto getSensorAlertStatusById(Long id);

  Page<SensorAlertStatusDto> getAllSensorAlertStatusList(int page, int size);
}
