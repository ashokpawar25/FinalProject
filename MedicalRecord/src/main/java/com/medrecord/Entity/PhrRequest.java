package com.medrecord.Entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class PhrRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "doctorUsername")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "patientUsername")
    private Patient patient;
    private boolean requestStatus;
    private LocalDate createdDate;
    private LocalDate lastUpdatedDate;

    public PhrRequest(Doctor doctor, Patient patient, boolean requestStatus, LocalDate createdDate, LocalDate lastUpdatedDate) {
        this.doctor = doctor;
        this.patient = patient;
        this.requestStatus = requestStatus;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public PhrRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public boolean isRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
}
