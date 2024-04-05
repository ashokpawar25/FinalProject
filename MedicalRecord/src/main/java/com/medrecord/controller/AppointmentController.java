package com.medrecord.controller;

import com.medrecord.Service.AppointmentServices;
import com.medrecord.requestdto.CreateAppointmentRequestDto;
import com.medrecord.responsedto.GetAllAppointmentResponseDto;
import com.medrecord.responsedto.PatientListForDoctorResponseDto;
import com.medrecord.responsedto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
//@RequestMapping("/patient")
public class AppointmentController {
    @Autowired
    AppointmentServices appointmentServices;

    @Autowired
    ServiceResponse response;

    @PostMapping("/patient/requestAppointment")
    public ResponseEntity<String> RequestAppointment(@RequestBody CreateAppointmentRequestDto requestDto) {

        response = appointmentServices.addNewAppointment(requestDto);

        if (!response.status) {
            return new ResponseEntity<String>(response.message, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(response.message);
    }

    @GetMapping("/getAllAppointments")
    public ResponseEntity<Object> getAllAppointments(@RequestParam(required = false) Integer appointmentId, @RequestParam(required = false) String doctorUsername, @RequestParam(required = false) String status, @RequestParam(required = false) String patientUserName) {
        List<GetAllAppointmentResponseDto> appointments = appointmentServices.getAllAppointments(appointmentId, doctorUsername, patientUserName, status);
        if (appointments.isEmpty()) return new ResponseEntity<>(response.message, HttpStatus.NOT_FOUND);
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

    @PutMapping("/updateAppointmentStatus/{appointmentId}")
    public ResponseEntity<String> updateAppointmentStatus(@PathVariable("appointmentId") int appointmentId, @RequestParam String status) {
        ServiceResponse response = appointmentServices.updateAppointmentStatus(appointmentId, status);
        if (!response.status)
            return new ResponseEntity<String>(response.message, HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(response.message);
    }

    @GetMapping("/patientList/{doctorUsername}")
    public ResponseEntity<List<PatientListForDoctorResponseDto>> getPatientList(@PathVariable("doctorUsername") String doctorUsername) {
        List<PatientListForDoctorResponseDto> patientList = appointmentServices.getPatientList(doctorUsername);
        return ResponseEntity.ok(patientList);
    }
}
