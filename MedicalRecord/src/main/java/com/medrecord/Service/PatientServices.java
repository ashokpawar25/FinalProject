package com.medrecord.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medrecord.Entity.Patient;
import com.medrecord.dao.PatientRepositery;
import com.medrecord.requestdto.PatientRegisterRequestDto;
import com.medrecord.requestdto.UpdatePatientRequestDto;

@Component
public class PatientServices 
{
	@Autowired
	PatientRepositery patientRepositery;
	
	public List<Patient> getPatients()
	{
		List<Patient> allPatients =patientRepositery.findAll();
		return allPatients;
	}
	
	public String registerPatient(PatientRegisterRequestDto requestDto)
	{
		Patient ExistingPatient = patientRepositery.findByUsername(requestDto.username);
		if(ExistingPatient != null)
		{
			return null;
		}

		Patient patient = new Patient();
		patient.setAddress(requestDto.address);
		patient.setAge(requestDto.age);
		patient.setAproved(false);
		patient.setBloodGroup(requestDto.bloodGroup);
		patient.setEmail(requestDto.email);
		patient.setGender(requestDto.gender);
		patient.setPassword(requestDto.password);
		patient.setUsername(requestDto.username);
		patient.setPatientName(requestDto.patientName);
		patient.setPhoneNo(requestDto.phoneNo);
		patient.setRole(requestDto.role);
		patient.setCreatedDate(new Date(System.currentTimeMillis()));
		patient.setLastUpdatedDate(new Date(System.currentTimeMillis()));
		
		patientRepositery.save(patient);
		return "Patient Registered Successfully";
	}
	
	
	public String updatePatient(String username,UpdatePatientRequestDto requestDto) 
	{
		var ExistingPatient = patientRepositery.findByUsername(username);
		if(ExistingPatient == null)
		{
			return null;
		}
		Patient patient = new Patient();
		
		patient.setAddress(requestDto.address);
		patient.setAge(requestDto.age);
		patient.setAproved(false);
		patient.setBloodGroup(requestDto.bloodGroup);
		patient.setEmail(requestDto.email);
		patient.setGender(requestDto.gender);
		patient.setPassword(requestDto.password);
		patient.setUsername(ExistingPatient.getUsername());
		patient.setPatientName(requestDto.patientName);
		patient.setPhoneNo(requestDto.phoneNo);
		patient.setCreatedDate(ExistingPatient.getCreatedDate());
		patient.setLastUpdatedDate(new Date(System.currentTimeMillis()));
		patient.setRole(ExistingPatient.getRole());
		
		patientRepositery.save(patient);
		return "Patient data updated successfully";
	}
	
	public String deletePatient(String username)
	{
		Patient existingPatient =patientRepositery.findByUsername(username);
		if (existingPatient == null) 
		{
			return null;
		}
		
		patientRepositery.delete(existingPatient);
		return "Patient deleted succesfully";
	}
}
