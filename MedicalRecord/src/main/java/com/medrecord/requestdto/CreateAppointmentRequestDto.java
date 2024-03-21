package com.medrecord.requestdto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateAppointmentRequestDto 
{
	 public LocalDate date;
	 public LocalTime time;
	 public String reason;
	 public String notes;
	 public String doctorUsername;
	 public String patientUsername;
}
