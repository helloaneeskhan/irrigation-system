package com.andela.irrigation.repository;

import com.andela.irrigation.entity.SensorAlertStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorAlertStatusRepository extends JpaRepository<SensorAlertStatus, Long> {}
