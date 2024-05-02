package com.medrecord.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class HealthRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany(mappedBy = "healthRecord", cascade = CascadeType.ALL)
    private List<UpdateEntry> updateEntries;

    private LocalDate createdDate;
    private LocalDate lastUpdatedDate;
    @OneToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    public HealthRecord(List<UpdateEntry> updateEntries, LocalDate createdDate, LocalDate lastUpdatedDate, Patient patient) {
        this.updateEntries = updateEntries;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.patient = patient;
    }

    public HealthRecord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<UpdateEntry> getUpdateEntries() {
        return updateEntries;
    }

    public void setUpdateEntries(List<UpdateEntry> updateEntries) {
        this.updateEntries = updateEntries;
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
