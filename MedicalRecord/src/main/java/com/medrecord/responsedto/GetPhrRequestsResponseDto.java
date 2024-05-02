package com.medrecord.responsedto;

import java.time.LocalDate;

public class GetPhrRequestsResponseDto {
    public int id;
    public String doctorName;
    public String patientName;
    public boolean requestStatus;
    public LocalDate createdDate;
    public LocalDate lastUpdatedDate;

    public GetPhrRequestsResponseDto(int id, String doctorName, String patientName, boolean requestStatus, LocalDate createdDate, LocalDate lastUpdatedDate) {
        this.id = id;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.requestStatus = requestStatus;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
    }
}
