package com.medrecord.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medrecord.Entity.Appointment;
import com.medrecord.Service.AppointmentServices;
import com.medrecord.requestdto.CreateAppointmentRequestDto;
import com.medrecord.responsedto.ServiceResponse;

@RestController
public class AppointmentController 
{
	@Autowired
	AppointmentServices appointmentServices;
	
	@Autowired
	ServiceResponse response;
	
	@PostMapping("/requestAppointment")
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
	public ResponseEntity<List<Appointment>> getAllAppointments()
	{
		List<Appointment> appointments = appointmentServices.getAllAppointments();
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
