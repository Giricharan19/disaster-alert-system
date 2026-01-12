package com.disaster.alert.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String message;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String severity;

    @Column(nullable = false)
    private String sentByRole;

    // 🔥 NEW: alert lifecycle
    @Column(nullable = false)
    private String status;   // NEW, ACCEPTED, COMPLETED

    // 🔥 NEW: assigned responder
    private String assignedTo;

    // 🔥 FIXED TIMESTAMP
    @Column(nullable = false)
    private LocalDateTime sentAt;

    public Alert() {
        this.sentAt = LocalDateTime.now();   // ALWAYS set
        this.status = "NEW";                 // default lifecycle
    }

    /* ================= GETTERS ================= */

    public Long getId() { return id; }
    public String getMessage() { return message; }
    public String getLocation() { return location; }
    public String getSeverity() { return severity; }
    public String getSentByRole() { return sentByRole; }
    public String getStatus() { return status; }
    public String getAssignedTo() { return assignedTo; }
    public LocalDateTime getSentAt() { return sentAt; }

    /* ================= SETTERS ================= */

    public void setId(Long id) { this.id = id; }
    public void setMessage(String message) { this.message = message; }
    public void setLocation(String location) { this.location = location; }
    public void setSeverity(String severity) { this.severity = severity; }
    public void setSentByRole(String sentByRole) { this.sentByRole = sentByRole; }
    public void setStatus(String status) { this.status = status; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
}
