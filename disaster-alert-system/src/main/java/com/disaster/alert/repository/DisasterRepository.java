package com.disaster.alert.repository;

import com.disaster.alert.model.Disaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisasterRepository extends JpaRepository<Disaster, Long> {

    List<Disaster> findByStatus(String status);
    List<Disaster> findByHandledByRole(String handledByRole);
    long countByStatus(String status);
    long countBySeverity(String severity);
    // ✅ HIGH RISK AREAS (REPORTS)
    @Query("SELECT d.location, COUNT(d) FROM Disaster d GROUP BY d.location ORDER BY COUNT(d) DESC")
    List<Object[]> findHighRiskAreas();

}
