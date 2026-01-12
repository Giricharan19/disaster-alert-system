# 🚨 Disaster Alert & Management System

A full-stack **Disaster Alert and Management System** designed to enable real-time disaster reporting, alert broadcasting, response coordination, and analytical insights for administrators.

This project simulates a real-world emergency response workflow involving **Citizens**, **Admins**, and **Responders**.

---

## 📌 Key Features

### 👤 Citizen Module
- Report disasters with location & severity
- Send alerts to administrators
- View broadcasted alerts

### 🛠 Admin Dashboard
- View all reported disasters and alerts
- Assign responders to incidents
- Monitor alert lifecycle (NEW → ACCEPTED → COMPLETED)
- View analytics & reports with charts

### 🚑 Responder Module
- View assigned alerts
- Accept and resolve incidents
- Track disaster handling history

### 📊 Reports & Analytics
- Total disasters and alerts
- Severity-wise distribution
- High-risk areas (location-based analysis)
- Alert engagement percentage
- Visualized using **Chart.js**

---

## 🧠 System Workflow

1. User Login (Citizen / Admin / Responder)
2. Dashboard Access
3. Disaster Reporting / Alert Broadcasting
4. Assignment & Acknowledgement
5. Tracking & Resolution
6. Continuous Disaster Preparedness

---

## 🏗 Technology Stack

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- RESTful APIs
- MySQL

### Frontend
- HTML5
- CSS3
- JavaScript (ES6)
- Chart.js

### Tools
- Maven
- Git & GitHub
- MySQL Workbench
- VS Code

To run the application: **mvnw.cmd spring-boot:run** (or) mvn spring-boot:run

