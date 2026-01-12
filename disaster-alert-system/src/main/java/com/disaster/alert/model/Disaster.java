package com.disaster.alert.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "disasters")
public class Disaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "disaster_type")
    private String disasterType;

    @Column(name = "location", length = 500)
    private String location;


    private String severity;

    private String status;

    @Column(name = "reported_by_role")
    private String reportedByRole;

    @Column(name = "handled_by_role")
    private String handledByRole;

    private Double latitude;

    private Double longitude;

    @Column(name = "reported_at")
    private LocalDateTime reportedAt;

    public Disaster() {
        this.reportedAt = LocalDateTime.now();
        this.status = "ACTIVE";
    }

    // ===== GETTERS & SETTERS =====

    public Long getId() { return id; }
    public String getDisasterType() { return disasterType; }
    public String getLocation() { return location; }
    public String getSeverity() { return severity; }
    public String getStatus() { return status; }
    public String getReportedByRole() { return reportedByRole; }
    public String getHandledByRole() { return handledByRole; }
    public Double getLatitude() { return latitude; }
    public Double getLongitude() { return longitude; }
    public LocalDateTime getReportedAt() { return reportedAt; }

    public void setId(Long id) { this.id = id; }
    public void setDisasterType(String disasterType) { this.disasterType = disasterType; }
    public void setLocation(String location) { this.location = location; }
    public void setSeverity(String severity) { this.severity = severity; }
    public void setStatus(String status) { this.status = status; }
    public void setReportedByRole(String reportedByRole) { this.reportedByRole = reportedByRole; }
    public void setHandledByRole(String handledByRole) { this.handledByRole = handledByRole; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public void setReportedAt(LocalDateTime reportedAt) { this.reportedAt = reportedAt; }
}
