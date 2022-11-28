package com.andela.irrigation.service.job;

import com.andela.irrigation.entity.IrrigationTimeSlot;
import com.andela.irrigation.entity.SensorAlertStatus;
import com.andela.irrigation.enums.IrrigationStatus;
import com.andela.irrigation.exception.SensorNotReachableException;
import com.andela.irrigation.repository.IrrigationTimeSlotRepository;
import com.andela.irrigation.repository.SensorAlertStatusRepository;
import com.andela.irrigation.service.SensorCommunicationService;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class IrrigationRetryWaterScheduleJob {

  @Autowired private IrrigationTimeSlotRepository irrigationTimeSlotRepository;

  @Autowired private SensorAlertStatusRepository sensorAlertStatusRepository;

  @Autowired(required = false)
  private SensorCommunicationService sensorCommunicationService;

  @Value("${irrigation.max.job.page.size:20}")
  private int maxPageSize;

  @Value("${irrigation.retry.attempt:5}")
  private Long irrigationRetryLimit;

  @Scheduled(cron = "${irrigation.timeslot.failed.cron:0 0/15 * * * ?}")
  public void processFailedIrrigation() {
    List<IrrigationStatus> irrigationStatuses =
        Arrays.asList(IrrigationStatus.FAILED, IrrigationStatus.RETRY_FAILED);
    LocalDateTime currentPeriod = LocalDateTime.now().minusMinutes(20);

    Pageable currentPage = PageRequest.of(0, maxPageSize);
    boolean hasNextPage = true;

    while (hasNextPage) {
      Page<IrrigationTimeSlot> irrigationTimeSlots =
          irrigationTimeSlotRepository
              .findAllByIrrigationStatusInAndIrrigationTimeGreaterThanEqualAndIrrigationAttemptLessThan(
                  irrigationStatuses, currentPeriod, irrigationRetryLimit, currentPage);

      irrigationTimeSlots.forEach(this::retryToIrrigate);

      currentPage = currentPage.next();
      hasNextPage = !irrigationTimeSlots.isLast();
    }
  }

  private void retryToIrrigate(IrrigationTimeSlot irrigationTimeSlot) {
    irrigationTimeSlot.setIrrigationStatus(IrrigationStatus.SUCCESS);
    irrigationTimeSlot.setLastAttemptAt(LocalDateTime.now());
    irrigationTimeSlot.setIrrigationAttempt(irrigationTimeSlot.getIrrigationAttempt() + 1);
    try {
      sensorCommunicationService.triggerSensor(irrigationTimeSlot);
    } catch (SensorNotReachableException exception) {
      irrigationTimeSlot.setIrrigationStatus(IrrigationStatus.RETRY_FAILED);
      irrigationTimeSlot =
          triggerAlertIfApplicable(irrigationTimeSlot, exception.getMessage(), exception.getCode());
    }
    irrigationTimeSlotRepository.save(irrigationTimeSlot);
  }

  private IrrigationTimeSlot triggerAlertIfApplicable(
      IrrigationTimeSlot irrigationTimeSlot, String errorMsg, int errorCode) {
    if (irrigationTimeSlot.getIrrigationAttempt() >= irrigationRetryLimit) {
      irrigationTimeSlot.setIrrigationStatus(IrrigationStatus.RETRY_LIMIT_EXCEED);
      SensorAlertStatus sensorAlertStatus =
          SensorAlertStatus.builder()
              .errorCode(errorCode)
              .errorMessage(errorMsg)
              .irrigationTimeSlot(irrigationTimeSlot)
              .build();
      sensorAlertStatusRepository.save(sensorAlertStatus);
    }
    return irrigationTimeSlot;
  }
}
