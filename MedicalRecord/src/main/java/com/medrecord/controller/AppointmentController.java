package com.medrecord.controller;

import java.util.List;
import java.util.Objects;

import com.medrecord.responsedto.GetAllAppointmentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medrecord.Entity.Appointment;
import com.medrecord.Service.AppointmentServices;
import com.medrecord.requestdto.CreateAppointmentRequestDto;
import com.medrecord.responsedto.ServiceResponse;

@RestController
//@RequestMapping("/patient")
public class AppointmentController 
{
	@Autowired
	AppointmentServices appointmentServices;
	
	@Autowired
	ServiceResponse response;
	
	@PostMapping("/patient/requestAppointment")
	public ResponseEntity<String> RequestAppointmet(@RequestBody CreateAppointmentRequestDto requestDto)
	{

	    response = appointmentServices.addNewAppointment(requestDto);

		if (response.status == false)
		{
			return new ResponseEntity<String>(response.message,HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(response.message);
	}
	
	@GetMapping("/getAllAppointments")
	public ResponseEntity<Object> getAllAppointments(@RequestParam(required = false) Integer appointmentId,@RequestParam(required = false)String doctorUsername,@RequestParam(required = false)boolean status,@RequestParam(required = false)String patientUserName)
	{
		List<GetAllAppointmentResponseDto> appointments = appointmentServices.getAllAppointments(appointmentId,doctorUsername,patientUserName,status);
		if(appointments.size()==0) return ResponseEntity.ok("No appointments found");
		return ResponseEntity.ok(appointments);
	}
	
	@PutMapping("/approveAppointment/{appointmentId}")
	public ResponseEntity<String> approveAppointment(@PathVariable ("appointmentId")int appointmentId)
	{
		ServiceResponse response = appointmentServices.approveAppointment(appointmentId);
		if(response.status == false)
			return new ResponseEntity<String>(response.message,HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(response.message);
	}

}
