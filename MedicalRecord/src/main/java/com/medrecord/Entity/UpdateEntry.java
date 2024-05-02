package com.medrecord.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public class UpdateEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String medicine;
    private String doseAndFrequency;
    private String symptoms;
    private String healthGoals;
    private String notes;
    private LocalDate createdDate;
    private LocalDate lastUpdatedDate;
    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "healthRecordId")
    private HealthRecord healthRecord;

    public UpdateEntry( String medicine, String doseAndFrequency, String symptoms, String healthGoals, String notes, LocalDate createdDate, LocalDate lastUpdatedDate, Doctor doctor, HealthRecord healthRecord) {
        this.medicine = medicine;
        this.doseAndFrequency = doseAndFrequency;
        this.symptoms = symptoms;
        this.healthGoals = healthGoals;
        this.notes = notes;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.doctor = doctor;
        this.healthRecord = healthRecord;
    }

    public UpdateEntry() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDoseAndFrequency() {
        return doseAndFrequency;
    }

    public void setDoseAndFrequency(String doseAndFrequency) {
        this.doseAndFrequency = doseAndFrequency;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getHealthGoals() {
        return healthGoals;
    }

    public void setHealthGoals(String healthGoals) {
        this.healthGoals = healthGoals;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public HealthRecord getHealthRecord() {
        return healthRecord;
    }
}
