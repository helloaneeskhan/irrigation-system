package com.andela.irrigation.service;

import com.andela.irrigation.entity.IrrigationTimeSlot;
import com.andela.irrigation.exception.SensorNotReachableException;

public interface SensorCommunicationService {
  void triggerSensor(IrrigationTimeSlot irrigationTimeSlot) throws SensorNotReachableException;
}
