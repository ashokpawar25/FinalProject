package com.medrecord.controller;

import java.util.List;

import com.medrecord.responsedto.GetAllAppointmentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

		if (!response.status)
		{
			return new ResponseEntity<String>(response.message,HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(response.message);
	}
	
	@GetMapping("/getAllAppointments")
	public ResponseEntity<Object> getAllAppointments(@RequestParam(required = false) Integer appointmentId,@RequestParam(required = false)String doctorUsername,@RequestParam(required = false)String status,@RequestParam(required = false)String patientUserName)
	{
		List<GetAllAppointmentResponseDto> appointments = appointmentServices.getAllAppointments(appointmentId,doctorUsername,patientUserName,status);
		if(appointments.isEmpty()) return ResponseEntity.ok("No appointments found");
		return ResponseEntity.ok(appointments);
	}
	
//	@PutMapping("/approveAppointment/{appointmentId}")
//	public ResponseEntity<String> approveAppointment(@PathVariable ("appointmentId")int appointmentId)
//	{
//		ServiceResponse response = appointmentServices.approveAppointment(appointmentId);
//		if(!response.status)
//			return new ResponseEntity<String>(response.message,HttpStatus.NOT_FOUND);
//		return ResponseEntity.ok(response.message);
//	}

	@PostMapping("/updateAppointmentStatus/{appointmentId}")
	public ResponseEntity<String> updateAppointmentStatus(@PathVariable("appointmentId")int appointmentId,@RequestParam String status)
	{
		ServiceResponse response = appointmentServices.updateAppointmentStatus(appointmentId,status);
		if(!response.status)
			return new ResponseEntity<String>(response.message,HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(response.message);
	}
}
