package com.disaster.alert.controller;

import com.disaster.alert.model.Alert;
import com.disaster.alert.model.User;
import com.disaster.alert.repository.AlertRepository;
import com.disaster.alert.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "*")
public class AlertController {

    private final AlertRepository alertRepository;
    private final UserRepository userRepository;

    public AlertController(AlertRepository alertRepository,
                           UserRepository userRepository) {
        this.alertRepository = alertRepository;
        this.userRepository = userRepository;
    }

    /* ================= CITIZEN SEND ALERT ================= */

    @PostMapping
    public ResponseEntity<Alert> sendAlert(@RequestBody Alert req) {

        Alert alert = new Alert();
        alert.setMessage(req.getMessage());
        alert.setLocation(req.getLocation());
        alert.setSeverity(req.getSeverity());
        alert.setSentByRole(req.getSentByRole());

        // defaults handled in entity constructor
        // status = NEW
        // sentAt = now

        return ResponseEntity.ok(alertRepository.save(alert));
    }

    /* ================= ADMIN ================= */

    // View all alerts
    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    // ✅ Get all responders (FIXED)
    @GetMapping("/responders")
    public List<User> getResponders() {
        return userRepository.findByRole("RESPONDER");
    }

    // Assign alert to responder
    @PatchMapping("/{id}/assign")
    public ResponseEntity<?> assignAlert(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found"));

        alert.setAssignedTo(body.get("assignedTo"));
        alert.setStatus("NEW");

        return ResponseEntity.ok(alertRepository.save(alert));
    }

    /* ================= RESPONDER ================= */

    // Responder: view alerts assigned to them
    @GetMapping("/responder/{email}")
    public List<Alert> getAlertsForResponder(@PathVariable String email) {
        return alertRepository.findByAssignedTo(email);
    }

    // Responder accepts alert
    @PatchMapping("/{id}/accept")
    public ResponseEntity<?> acceptAlert(@PathVariable Long id) {

        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found"));

        alert.setStatus("ACCEPTED");
        return ResponseEntity.ok(alertRepository.save(alert));
    }

    // Responder completes alert
    @PatchMapping("/{id}/complete")
    public ResponseEntity<?> completeAlert(@PathVariable Long id) {

        Alert alert = alertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found"));

        alert.setStatus("COMPLETED");
        return ResponseEntity.ok(alertRepository.save(alert));
    }
}
