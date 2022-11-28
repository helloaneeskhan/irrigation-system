package com.andela.irrigation.service.impl;

import com.andela.irrigation.dto.SensorAlertStatusDto;
import com.andela.irrigation.entity.SensorAlertStatus;
import com.andela.irrigation.exception.RecordNotFoundException;
import com.andela.irrigation.repository.SensorAlertStatusRepository;
import com.andela.irrigation.service.SensorAlertStatusService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SensorAlertStatusServiceImpl implements SensorAlertStatusService {

  @Autowired private SensorAlertStatusRepository sensorAlertStatusRepository;

  @Override
  public SensorAlertStatusDto getSensorAlertStatusById(Long id) {
    Optional<SensorAlertStatus> sensorAlertStatusOptional =
        sensorAlertStatusRepository.findById(id);
    if (!sensorAlertStatusOptional.isPresent())
      throw new RecordNotFoundException(
          String.format("Sensor Alert Status with id %d not found", id));

    return sensorAlertStatusOptional.map(SensorAlertStatusDto::fromEntity).get();
  }

  @Override
  public Page<SensorAlertStatusDto> getAllSensorAlertStatusList(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<SensorAlertStatus> sensorAlertStatuses = sensorAlertStatusRepository.findAll(pageable);
    return sensorAlertStatuses.map(SensorAlertStatusDto::fromEntity);
  }
}
