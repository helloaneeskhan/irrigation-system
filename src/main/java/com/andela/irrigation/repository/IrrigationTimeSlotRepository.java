package com.andela.irrigation.repository;

import com.andela.irrigation.entity.IrrigationTimeSlot;
import com.andela.irrigation.enums.IrrigationStatus;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrrigationTimeSlotRepository extends JpaRepository<IrrigationTimeSlot, Long> {
  Page<IrrigationTimeSlot> findAllByPlot_id(Long plotId, Pageable pageable);

  Page<IrrigationTimeSlot> findAllByIrrigationStatusInAndIrrigationTimeGreaterThanEqual(
      List<IrrigationStatus> irrigationStatuses, LocalDateTime irrigationTime, Pageable pageable);

  Page<IrrigationTimeSlot>
      findAllByIrrigationStatusInAndIrrigationTimeGreaterThanEqualAndIrrigationAttemptLessThan(
          List<IrrigationStatus> irrigationStatuses,
          LocalDateTime irrigationTime,
          Long irrigationAttempt,
          Pageable pageable);
}
