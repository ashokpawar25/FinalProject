package com.medrecord.Service;

import java.time.LocalDate;
import java.util.List;

import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medrecord.Entity.Appointment;
import com.medrecord.Entity.Doctor;
import com.medrecord.Entity.Patient;
import com.medrecord.dao.AppointmentRepositery;
import com.medrecord.dao.DoctorRepositery;
import com.medrecord.dao.PatientRepositery;
import com.medrecord.requestdto.CreateAppointmentRequestDto;
import com.medrecord.responsedto.ServiceResponse;

@Service
public class AppointmentServices {
	
	@Autowired
	PatientRepositery patientRepositery;
	
	@Autowired
	DoctorRepositery doctorRepositery;
	
	@Autowired
	AppointmentRepositery appointmentRepositery;
	
	@Autowired
	ServiceResponse response;

	public ServiceResponse addNewAppointment(CreateAppointmentRequestDto requestDto) 
	{
		Patient patient = patientRepositery.findByUsername(requestDto.patientUsername);
		if(patient == null)
		{
			return new ServiceResponse(false,"Patient not found");
		}
		
		Doctor doctor = doctorRepositery.findByUsername(requestDto.doctorUsername);
		if (doctor == null)
		{
			return new ServiceResponse(false,"Doctor not found");
		}
		if(requestDto.date.isBefore(LocalDate.now()))
		{
			return new ServiceResponse(false,"Enter incoming Dates");
		}
		Appointment appointment = new Appointment();
		appointment.setDate(requestDto.date);
		appointment.setTime(requestDto.time);
		appointment.setNotes(requestDto.notes);
		appointment.setReason(requestDto.reason);
		appointment.setApproved(false);
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		appointment.setRequestedDate(LocalDate.now());
		appointment.setLastUpdatedDate(LocalDate.now());
		
		appointmentRepositery.save(appointment);
		return new ServiceResponse(true,"Appointment submitted successfully");
	}

	public List<Appointment> getAllAppointments() {
		List<Appointment> appointments = appointmentRepositery.findAll();
		return appointments;
	}

	public ServiceResponse approveAppointment(int appointmentID) {
		
		Appointment appointment = appointmentRepositery.findByAppointmentId(appointmentID);
		if(appointment == null)
		{
			return new ServiceResponse(false,"Appointment not found");
		}
		else if (appointment.isApproved())
		{
			return new ServiceResponse(false,"Appointment already approved");
		}
		appointment.setApproved(true);
		appointmentRepositery.save(appointment);
		return new ServiceResponse(true,"Appointment is approved");
	}

}
