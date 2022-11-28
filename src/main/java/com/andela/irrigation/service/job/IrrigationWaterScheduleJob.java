package com.andela.irrigation.service.job;

import com.andela.irrigation.entity.IrrigationTimeSlot;
import com.andela.irrigation.enums.IrrigationStatus;
import com.andela.irrigation.exception.SensorNotReachableException;
import com.andela.irrigation.repository.IrrigationTimeSlotRepository;
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
public class IrrigationWaterScheduleJob {

  @Autowired private IrrigationTimeSlotRepository irrigationTimeSlotRepository;

  @Autowired(required = false)
  private SensorCommunicationService sensorCommunicationService;

  @Value("${irrigation.max.job.page.size:20}")
  private int maxPageSize;

  @Scheduled(cron = "${irrigation.timeslot.pending.cron:0 0 0/1 * * ?}")
  public void processPendingIrrigation() {
    List<IrrigationStatus> irrigationStatuses = Arrays.asList(IrrigationStatus.PENDING);
    LocalDateTime currentPeriod = LocalDateTime.now().minusHours(2);

    Pageable currentPage = PageRequest.of(0, maxPageSize);
    boolean hasNextPage = true;

    while (hasNextPage) {
      Page<IrrigationTimeSlot> irrigationTimeSlots =
          irrigationTimeSlotRepository.findAllByIrrigationStatusInAndIrrigationTimeGreaterThanEqual(
              irrigationStatuses, currentPeriod, currentPage);

      irrigationTimeSlots.forEach(this::irrigate);

      currentPage = currentPage.next();
      hasNextPage = !irrigationTimeSlots.isLast();
    }
  }

  private void irrigate(IrrigationTimeSlot irrigationTimeSlot) {
    irrigationTimeSlot.setIrrigationStatus(IrrigationStatus.SUCCESS);
    irrigationTimeSlot.setLastAttemptAt(LocalDateTime.now());
    irrigationTimeSlot.setIrrigationAttempt(irrigationTimeSlot.getIrrigationAttempt() + 1);
    try {
      sensorCommunicationService.triggerSensor(irrigationTimeSlot);
    } catch (SensorNotReachableException e) {
      irrigationTimeSlot.setIrrigationStatus(IrrigationStatus.FAILED);
    }

    irrigationTimeSlotRepository.save(irrigationTimeSlot);
  }
}
