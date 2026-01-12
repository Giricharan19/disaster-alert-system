package com.disaster.alert.controller;

import com.disaster.alert.repository.AlertRepository;
import com.disaster.alert.repository.DisasterRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportsController {

    private final DisasterRepository disasterRepository;
    private final AlertRepository alertRepository;

    public ReportsController(DisasterRepository disasterRepository,
                             AlertRepository alertRepository) {
        this.disasterRepository = disasterRepository;
        this.alertRepository = alertRepository;
    }

    @GetMapping("/ping")
    public String ping() {
        return "REPORTS WORKING";
    }

    @GetMapping("/summary")
    public Map<String, Object> getReportsSummary() {

        Map<String, Object> response = new HashMap<>();

        Map<String, Object> disasters = new HashMap<>();
        disasters.put("total", disasterRepository.count());
        disasters.put("active", disasterRepository.countByStatus("ACTIVE"));
        disasters.put("inProgress", disasterRepository.countByStatus("IN_PROGRESS"));
        disasters.put("resolved", disasterRepository.countByStatus("RESOLVED"));
        disasters.put("lowSeverity", disasterRepository.countBySeverity("Low"));
        disasters.put("moderateSeverity", disasterRepository.countBySeverity("Moderate"));
        disasters.put("highSeverity", disasterRepository.countBySeverity("High"));
        response.put("disasters", disasters);

        Map<String, Object> alerts = new HashMap<>();
        alerts.put("total", alertRepository.count());
        alerts.put("new", alertRepository.countByStatus("NEW"));
        alerts.put("accepted", alertRepository.countByStatus("ACCEPTED"));
        alerts.put("completed", alertRepository.countByStatus("COMPLETED"));
        alerts.put("lowSeverity", alertRepository.countBySeverity("Low"));
        alerts.put("moderateSeverity", alertRepository.countBySeverity("Moderate"));
        alerts.put("highSeverity", alertRepository.countBySeverity("High"));
        response.put("alerts", alerts);

        List<Object[]> highRiskAreas = disasterRepository.findHighRiskAreas();
        response.put("highRiskAreas", highRiskAreas);

        long acceptedAlerts = alertRepository.countByStatus("ACCEPTED");
        long totalAlerts = alertRepository.count();
        int engagementPercent =
                totalAlerts == 0 ? 0 : (int) ((acceptedAlerts * 100) / totalAlerts);
        response.put("engagementPercent", engagementPercent);

        return response;
    }
}
