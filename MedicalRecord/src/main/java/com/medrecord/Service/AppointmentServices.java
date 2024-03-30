package com.medrecord.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.medrecord.responsedto.GetAllAppointmentResponseDto;
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

	public List<GetAllAppointmentResponseDto> getAllAppointments(Integer appointmentId, String doctorUsername, String patientUserName, Boolean status) {
		List<Appointment> appointments = appointments = appointmentRepositery.findAll();
		if (appointmentId != null)
		{
			appointments = appointments.stream().filter(a -> a.getAppointmentId() == appointmentId).collect(Collectors.toList());
		}
		if(doctorUsername != null)
		{
			Doctor doctor = doctorRepositery.findByUsername(doctorUsername);
			appointments = appointments.stream().filter(a -> a.getDoctor() == doctor).collect(Collectors.toList());
		}
		if(patientUserName != null)
		{
			Patient patient = patientRepositery.findByUsername(patientUserName);
			appointments = appointments.stream().filter(a -> a.getPatient() == patient).collect(Collectors.toList());
		}
		if (status != null)
		{
			appointments = appointments.stream()
					.filter(a -> a.isApproved() == status)
					.collect(Collectors.toList());
		}

		return appointments.stream()
				.map(appointment -> {
					GetAllAppointmentResponseDto dto = new GetAllAppointmentResponseDto();
					dto.appointmentId = appointment.getAppointmentId();
					dto.patientUsername = appointment.getPatient().getUsername();
					dto.doctorUsername = appointment.getDoctor().getUsername();
					dto.date = appointment.getDate();
					dto.time = appointment.getTime();
					dto.reason = appointment.getReason();
					dto.notes=appointment.getNotes();
					dto.requestedDate = appointment.getRequestedDate();
					dto.lastUpdatedDate  = appointment.getLastUpdatedDate();
					dto.approved =appointment.isApproved();
					return dto;
				})
				.collect(Collectors.toList());
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
