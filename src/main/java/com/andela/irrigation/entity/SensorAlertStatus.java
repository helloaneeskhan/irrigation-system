package com.andela.irrigation.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SensorAlertStatus {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private Integer errorCode;

  @Column private String errorMessage;

  @Column private LocalDateTime lastRetry;
  @OneToOne private IrrigationTimeSlot irrigationTimeSlot;
}
