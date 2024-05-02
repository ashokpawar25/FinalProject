package com.medrecord.requestdto;

import com.medrecord.Entity.Doctor;
import com.medrecord.Entity.HealthRecord;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class CreateUpdateEntryRequestDto {
    public String medicine;
    public String doseAndFrequency;
    public String symptoms;
    public String healthGoals;
    public String notes;
    public String doctorUsername;
    public int healthRecordId;
}
