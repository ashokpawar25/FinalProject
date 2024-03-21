package com.medrecord.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int appointmentId;
	
	@ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "doctorId")
    private Doctor doctor;
	
	private LocalDate date;
	private LocalTime time;
	private String reason;
	private String notes;
	private boolean isApproved;
	private LocalDate requestedDate;
	private LocalDate lastUpdatedDate;
	
	
	
	public Appointment() {
		
	}
	
	public Appointment(int appointmentId, Patient patientId, Doctor doctorId, LocalDate date,LocalTime time, String reason,
			String notes,boolean isApproved, LocalDate requestedDate, LocalDate lastUpdatedDate) {
		super();
		this.appointmentId = appointmentId;
		this.patient = patientId;
		this.doctor = doctorId;
		this.date = date;
		this.time = time;
		this.reason = reason;
		this.notes = notes;
		this.isApproved = isApproved;
		this.requestedDate = requestedDate;
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public LocalDate getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(LocalDate requestedDate) {
		this.requestedDate = requestedDate;
	}

	public LocalDate getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
}
