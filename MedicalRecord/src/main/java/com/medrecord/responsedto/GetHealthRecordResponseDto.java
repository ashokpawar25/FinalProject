package com.medrecord.responsedto;

import java.time.LocalDate;

public class GetHealthRecordResponseDto {
    public int id;
    public String medicine;
    public  String doseAndFrequency;
    public String symptoms;
    public String healthGoals;
    public String notes;
    public LocalDate createdDate;
    public LocalDate lastUpdatedDate;
    public String doctorUsername;

    public GetHealthRecordResponseDto(int id, String medicine, String doseAndFrequency, String symptoms, String healthGoals, String notes, LocalDate createdDate, LocalDate lastUpdatedDate, String doctorUsername) {
        this.id = id;
        this.medicine = medicine;
        this.doseAndFrequency = doseAndFrequency;
        this.symptoms = symptoms;
        this.healthGoals = healthGoals;
        this.notes = notes;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.doctorUsername = doctorUsername;
    }
}
