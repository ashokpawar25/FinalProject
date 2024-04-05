package com.medrecord.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medrecord.Entity.Appointment;

import java.util.List;


public interface AppointmentRepositery extends JpaRepository<Appointment, Integer> {
	Appointment findByAppointmentId(int appointmentId);
	List<Appointment> findByDoctorUsername(String doctorUsername);
}
