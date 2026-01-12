package com.disaster.alert.repository;

import com.disaster.alert.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByAssignedTo(String assignedTo);

    // ===== REPORTS & ANALYTICS =====
    long countByStatus(String status);
    long countBySeverity(String severity);
}
