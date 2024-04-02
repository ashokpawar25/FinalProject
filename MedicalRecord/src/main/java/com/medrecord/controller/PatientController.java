package com.medrecord.controller;

import com.medrecord.Entity.Patient;
import com.medrecord.Service.PatientServices;
import com.medrecord.dao.PatientRepositery;
import com.medrecord.requestdto.PatientRegisterRequestDto;
import com.medrecord.requestdto.UpdatePatientRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientServices patientServices;

    @Autowired
    PatientRepositery patientRepositery;

    @PostMapping("/login")
    public ResponseEntity<?> loginPatient(@RequestParam String username, @RequestParam String password) {
        if (username == null || password == null) {
            return new ResponseEntity<String>("Username And Password Are Required", HttpStatus.BAD_REQUEST);
        }

        Patient existingPatient = patientRepositery.findByUsername(username);

        if (existingPatient == null || !existingPatient.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or passwords");
        }
        return ResponseEntity.ok(existingPatient);
    }

    @GetMapping("/getPatients")
    public ResponseEntity<List<Patient>> getPatientsData() {
        var response = patientServices.getPatients();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> testing(@RequestBody PatientRegisterRequestDto requestDto) {
        var response = patientServices.registerPatient(requestDto);
        if (response == null) {
            return new ResponseEntity<>("Username already exist please use different username", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updatePatient/{username}")
    public ResponseEntity<String> updatePatient(@PathVariable("username") String username, @RequestBody UpdatePatientRequestDto requestDto) {
        var response = patientServices.updatePatient(username, requestDto);
        if (response == null) {
            return new ResponseEntity<String>("Patient data not found", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/deletePatient/{username}")
    public ResponseEntity<String> deletePatient(@PathVariable("username") String username) {
        var response = patientServices.deletePatient(username);
        if (response == null) {
            return new ResponseEntity<String>("Patient Data not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }
}
