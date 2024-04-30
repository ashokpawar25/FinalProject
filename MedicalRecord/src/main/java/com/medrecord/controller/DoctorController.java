package com.medrecord.controller;

import java.util.List;

import com.medrecord.requestdto.UpdateDoctorRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medrecord.Entity.Doctor;
import com.medrecord.Service.DoctorServices;
import com.medrecord.dao.DoctorRepository;
import com.medrecord.requestdto.DoctorRegisterRequestDto;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/doctor")
public class DoctorController 
{
	@Autowired
	DoctorServices doctorServices;
	
//	@GetMapping("/login")
//	public ResponseEntity<?> loginDoctor(@RequestParam String username,@RequestParam String password)
//	{
//		if(username == null || password == null)
//		{
//			return new ResponseEntity<String>("Username And Password Are Required",HttpStatus.BAD_REQUEST);
//		}
//
//		Doctor existingDoctor = doctorRepositery.findByUsername(username);
//		if(existingDoctor == null || !existingDoctor.getPassword().equals(password))
//		{
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or passwords");
//		}
//		return ResponseEntity.ok(existingDoctor);
//	}
	
	@GetMapping("/getDoctors")
	public ResponseEntity<List<Doctor>> getAllDoctors()
	{
		 List<Doctor> response = doctorServices.getDoctors();
		 return ResponseEntity.ok(response);
	}
	
	@PostMapping("/doctorRegistration")
	public ResponseEntity<String> doctorRegistration(@RequestBody DoctorRegisterRequestDto requestDto) {
		String response = doctorServices.registerDoctor(requestDto);
		if (response == null)
		{
			return new ResponseEntity<>("Username Already Exist Please use different username",HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/updateDoctor/{username}")
	public ResponseEntity<String> updateDoctorData(@PathVariable("username")String username,@RequestBody UpdateDoctorRequestDto requestDto)
	{
		String response = doctorServices.updateDoctor(username, requestDto);
		if(response == null)
		{
			return new ResponseEntity<String>("Doctor Data not found",HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("deleteDoctor/{username}")
	public ResponseEntity<String> deleteDoctor(@PathVariable("username")String username)
	{
		var response = doctorServices.deleteDoctor(username);
		if(response == null)
		{
			return new ResponseEntity<String>("Doctor Data Not Found",HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(response);
	}
}
