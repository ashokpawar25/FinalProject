package com.medrecord.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medrecord.Entity.Appointment;


public interface AppointmentRepositery extends JpaRepository<Appointment, Integer> {
	Appointment findByAppointmentId(int appointmentId);
}
