package com.disaster.alert.controller;

import com.disaster.alert.model.Disaster;
import com.disaster.alert.repository.DisasterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disasters")
@CrossOrigin(origins = "*")
public class DisasterController {

    private final DisasterRepository disasterRepository;

    public DisasterController(DisasterRepository disasterRepository) {
        this.disasterRepository = disasterRepository;
    }

    // ================= CITIZEN REPORT =================
    @PostMapping("/report")
    public ResponseEntity<Disaster> reportDisaster(@RequestBody Disaster req) {

        Disaster disaster = new Disaster();
        disaster.setDisasterType(req.getDisasterType());
        disaster.setSeverity(req.getSeverity());
        disaster.setLatitude(req.getLatitude());
        disaster.setLongitude(req.getLongitude());
        disaster.setLocation(req.getLocation());
        disaster.setReportedByRole(req.getReportedByRole());
        disaster.setStatus("ACTIVE");

        return ResponseEntity.ok(disasterRepository.save(disaster));
    }

    // ================= ADMIN =================
    @GetMapping
    public List<Disaster> getAll() {
        return disasterRepository.findAll();
    }

    // ================= RESPONDER =================
    @GetMapping("/active")
    public List<Disaster> getActive() {
        return disasterRepository.findByStatus("ACTIVE");
    }

    // ================= ADMIN ASSIGN =================
    @PatchMapping("/{id}/assign")
    public ResponseEntity<?> assign(@PathVariable Long id, @RequestBody Disaster d) {

        Disaster disaster = disasterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disaster not found"));

        disaster.setHandledByRole(d.getHandledByRole());
        return ResponseEntity.ok(disasterRepository.save(disaster));
    }

    // ================= RESPONDER ACCEPT =================
@PatchMapping("/{id}/accept")
public ResponseEntity<?> acceptDisaster(@PathVariable Long id) {
    Disaster d = disasterRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Disaster not found"));

    d.setStatus("IN_PROGRESS");
    return ResponseEntity.ok(disasterRepository.save(d));
}

// ================= RESPONDER COMPLETE =================
@PatchMapping("/{id}/complete")
public ResponseEntity<?> completeDisaster(@PathVariable Long id) {
    Disaster d = disasterRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Disaster not found"));

    d.setStatus("RESOLVED");
    return ResponseEntity.ok(disasterRepository.save(d));
}
// ================= RESPONDER HISTORY =================
@GetMapping("/responder/{email}")
public List<Disaster> getResponderDisasters(@PathVariable String email) {
    return disasterRepository.findByHandledByRole(email);
}


}
