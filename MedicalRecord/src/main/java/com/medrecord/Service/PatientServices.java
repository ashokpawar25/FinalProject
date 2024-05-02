package com.medrecord.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.medrecord.Entity.ApproveRequest;
import com.medrecord.dao.ApproveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medrecord.Entity.Patient;
import com.medrecord.dao.PatientRepository;
import com.medrecord.requestdto.PatientRegisterRequestDto;
import com.medrecord.requestdto.UpdatePatientRequestDto;

@Component
public class PatientServices 
{
	@Autowired
	PatientRepository patientRepository;

	@Autowired
	ApproveRequestRepository approveRequestRepository;
	
	public List<Patient> getPatients()
	{
        return patientRepository.findAll();
	}
	
	public String registerPatient(PatientRegisterRequestDto requestDto)
	{
		Patient ExistingPatient = patientRepository.findByUsername(requestDto.username);
		if(ExistingPatient != null)
		{
			return null;
		}

		Patient patient = new Patient();
		patient.setAddress(requestDto.address);
		patient.setAge(requestDto.age);
		patient.setApproved(false);
		patient.setBloodGroup(requestDto.bloodGroup);
		patient.setEmail(requestDto.email);
		patient.setGender(requestDto.gender);
		patient.setPassword(requestDto.password);
		patient.setUsername(requestDto.username);
		patient.setPatientName(requestDto.patientName);
		patient.setPhoneNo(requestDto.phoneNo);
		patient.setRole("patient");
		patient.setCreatedDate(new Date(System.currentTimeMillis()));
		patient.setLastUpdatedDate(new Date(System.currentTimeMillis()));

		patientRepository.save(patient);
		ApproveRequest approveRequest = new ApproveRequest(requestDto.username, requestDto.patientName, requestDto.address, requestDto.phoneNo,
				requestDto.email, requestDto.gender,"patient", LocalDate.now(),LocalDate.now());
		approveRequestRepository.save(approveRequest);
		return "Patient Registered Successfully";
	}
	
	
	public String updatePatient(String username,UpdatePatientRequestDto requestDto) 
	{
		var ExistingPatient = patientRepository.findByUsername(username);
		if(ExistingPatient == null)
		{
			return null;
		}
		Patient patient = new Patient();
		
		patient.setAddress(requestDto.address);
		patient.setAge(requestDto.age);
		patient.setApproved(false);
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

		patientRepository.save(patient);
		return "Patient data updated successfully";
	}
	
	public String deletePatient(String username)
	{
		Patient existingPatient = patientRepository.findByUsername(username);
		if (existingPatient == null) 
		{
			return null;
		}
		
		patientRepository.delete(existingPatient);
		return "Patient deleted successfully";
	}
}
