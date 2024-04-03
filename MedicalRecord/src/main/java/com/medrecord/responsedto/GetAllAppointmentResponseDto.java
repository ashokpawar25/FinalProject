package com.medrecord.responsedto;

import java.time.LocalDate;
import java.time.LocalTime;

public class GetAllAppointmentResponseDto
{
    public int appointmentId;
    public String patientUsername;
    public String doctorUsername;
    public LocalDate date;
    public LocalTime time;
    public String reason;
    public String notes;
    public LocalDate requestedDate;
    public LocalDate lastUpdatedDate;
    public String status;
}
