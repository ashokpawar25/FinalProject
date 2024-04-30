package com.medrecord.controller;

import com.medrecord.Entity.Doctor;
import com.medrecord.Entity.Patient;
import com.medrecord.dao.DoctorRepository;
import com.medrecord.dao.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController
{
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;


    @GetMapping("/login")
    public ResponseEntity<Object> login(@RequestParam String username, @RequestParam String password)
    {
        if(username == null || password == null)
        {
            return new ResponseEntity("Username And Password Are Required", HttpStatus.BAD_REQUEST);
        }
        Doctor existingDoctor = doctorRepository.findByUsername(username);
        if(existingDoctor != null)
        {
            if(existingDoctor.getPassword().equals(password))
            {
                return ResponseEntity.ok(existingDoctor);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or passwords");
        }

        Patient existingPatient = patientRepository.findByUsername(username);
        if(existingPatient != null)
        {
            if(existingPatient.getPassword().equals(password))
            {
                return ResponseEntity.ok(existingPatient);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or passwords");
        }
        return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
    }
}

















