package com.medrecord.controller;

import com.medrecord.Service.AdminService;
import com.medrecord.responsedto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController
{
    @Autowired
    AdminService adminService;

    @PutMapping("/approvePatient/{patientUsername}")
    public ResponseEntity<String> approvePatient(@PathVariable("patientUsername")String patientUsername)
    {
        ServiceResponse response = adminService.approvePatient(patientUsername);
        if (response.status)
            return ResponseEntity.ok(response.message);

        return new ResponseEntity<>(response.message,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/rejectPatient/{patientUsername}")
    public ResponseEntity<String> rejectPatient(@PathVariable("patientUsername")String patientUsername)
    {
        ServiceResponse response = adminService.rejectPatient(patientUsername);
        if (response.status)
            return ResponseEntity.ok(response.message);

        return new ResponseEntity<>(response.message,HttpStatus.NOT_FOUND);
    }
}
