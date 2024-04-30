package com.medrecord.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.medrecord.responsedto.GetAllAppointmentResponseDto;
import com.medrecord.responsedto.PatientListForDoctorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medrecord.Entity.Appointment;
import com.medrecord.Entity.Doctor;
import com.medrecord.Entity.Patient;
import com.medrecord.dao.AppointmentRepository;
import com.medrecord.dao.DoctorRepository;
import com.medrecord.dao.PatientRepository;
import com.medrecord.requestdto.CreateAppointmentRequestDto;
import com.medrecord.responsedto.ServiceResponse;

@Service
public class AppointmentServices {
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	AppointmentRepository appointmentRepository;

	public ServiceResponse addNewAppointment(CreateAppointmentRequestDto requestDto) 
	{
		Patient patient = patientRepository.findByUsername(requestDto.patientUsername);
		if(patient == null)
		{
			return new ServiceResponse(false,"Patient not found");
		}
		
		Doctor doctor = doctorRepository.findByUsername(requestDto.doctorUsername);
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
		appointment.setStatus("pending");
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		appointment.setRequestedDate(LocalDate.now());
		appointment.setLastUpdatedDate(LocalDate.now());
		
		appointmentRepository.save(appointment);
		return new ServiceResponse(true,"Appointment submitted successfully");
	}

	public List<GetAllAppointmentResponseDto> getAllAppointments(Integer appointmentId, String doctorUsername, String patientUserName, String status) {
		List<Appointment> appointments = appointmentRepository.findAll();
		if (appointmentId != null)
		{
			appointments = appointments.stream().filter(a -> a.getAppointmentId() == appointmentId).collect(Collectors.toList());
		}
		if(doctorUsername != null)
		{
			Doctor doctor = doctorRepository.findByUsername(doctorUsername);
			appointments = appointments.stream().filter(a -> a.getDoctor() == doctor).collect(Collectors.toList());
		}
		if(patientUserName != null)
		{
			Patient patient = patientRepository.findByUsername(patientUserName);
			appointments = appointments.stream().filter(a -> a.getPatient() == patient).collect(Collectors.toList());
		}
		if (status != null)
		{
			appointments = appointments.stream()
					.filter(a -> a.getStatus().equalsIgnoreCase(status))
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
					dto.status =appointment.getStatus();
					return dto;
				})
				.collect(Collectors.toList());
	}

//	public ServiceResponse approveAppointment(int appointmentId) {
//
//		Appointment appointment = appointmentRepositery.findByAppointmentId(appointmentId);
//		if(appointment == null)
//		{
//			return new ServiceResponse(false,"Appointment not found");
//		}
//		appointment.setStatus("Approved");
//		appointmentRepositery.save(appointment);
//		return new ServiceResponse(true,"Appointment is approved");
//	}

	public ServiceResponse updateAppointmentStatus(int appointmentId,String status)
	{
		Appointment appointment = appointmentRepository.findByAppointmentId(appointmentId);
		if(appointment == null)
		{
			return new ServiceResponse(false,"Appointment not found");
		}
		appointment.setStatus(status);
		appointmentRepository.save(appointment);
		return new ServiceResponse(true,"Appointment updated successfully");
	}

	public List<PatientListForDoctorResponseDto> getPatientList(String doctorUsername)
	{
		List<PatientListForDoctorResponseDto> patientList = new ArrayList<>();
		List<Appointment> allAppointments = appointmentRepository.findAll().stream()
				.filter(appointment -> appointment.getDoctor().getUsername().equalsIgnoreCase(doctorUsername)).toList();

		for(Appointment appointment:allAppointments)
		{
			if(appointment.getStatus().equalsIgnoreCase("Approved"))
			{
				Patient patient = patientRepository.findByUsername(appointment.getPatient().getUsername());
				if(patient != null)
				{
					PatientListForDoctorResponseDto dto = new PatientListForDoctorResponseDto();
					dto.patientName = patient.getPatientName();
					dto.phoneNo = patient.getPhoneNo();
					dto.gender = patient.getGender();
					dto.age = patient.getAge();
					dto.dateOfVisit = appointment.getDate();

					patientList.add(dto);
				}
			}
		}
		return patientList;
	}
}
