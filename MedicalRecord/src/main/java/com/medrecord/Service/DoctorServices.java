package com.medrecord.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.medrecord.Entity.ApproveRequest;
import com.medrecord.dao.ApproveRequestRepository;
import com.medrecord.requestdto.UpdateDoctorRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.medrecord.Entity.Doctor;
import com.medrecord.dao.DoctorRepository;
import com.medrecord.requestdto.DoctorRegisterRequestDto;

@Component
public class DoctorServices 
{
	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	ApproveRequestRepository approveRequestRepository;
	
	public List<Doctor> getDoctors() 
	{
		List<Doctor> doctors= doctorRepository.findAll();
		return doctors;
	}
	
	public String registerDoctor(DoctorRegisterRequestDto requestDto)
	{
		Doctor existingUsername = doctorRepository.findByUsername(requestDto.username);
		if(existingUsername!=null)
		{
			return null;
		}
		Doctor doctor= new Doctor();
		doctor.setAddress(requestDto.address);
		doctor.setAge(requestDto.age);
		doctor.setEmail(requestDto.email);
		doctor.setFirstName(requestDto.firstName);
		doctor.setLastName(requestDto.lastName);
		doctor.setGender(requestDto.gender);
		doctor.setLicenseNumber(requestDto.licenseNumber);
		doctor.setMobile(requestDto.mobile);
		doctor.setPassword(requestDto.password);
		doctor.setSpecialization(requestDto.specialization);
		doctor.setUsername(requestDto.username);
		doctor.setRole("doctor");
		doctor.setCreatedDate(new Date(System.currentTimeMillis()));
		doctor.setLastUpdatedDate(new Date(System.currentTimeMillis()));
		
		doctorRepository.save(doctor);
		ApproveRequest approveRequest = new ApproveRequest(requestDto.username,requestDto.firstName+" "+requestDto.lastName, requestDto.address, requestDto.mobile,
				requestDto.email, requestDto.gender,"doctor", LocalDate.now(),LocalDate.now());
		approveRequestRepository.save(approveRequest);
		return "Doctor Registered Successfully";
	}
	
	public String updateDoctor(String username, UpdateDoctorRequestDto requestDto)
	{
		Doctor existingDoctor = doctorRepository.findByUsername(username);
		if(existingDoctor == null)
		{
			return null;
		}
		
		existingDoctor.setAddress(requestDto.address);
		existingDoctor.setAge(requestDto.age);
		existingDoctor.setEmail(requestDto.email);
		existingDoctor.setFirstName(requestDto.firstName);
		existingDoctor.setLastName(requestDto.lastName);
		existingDoctor.setGender(requestDto.gender);
		existingDoctor.setLicenseNumber(requestDto.licenseNumber);
		existingDoctor.setMobile(requestDto.mobile);
		existingDoctor.setPassword(requestDto.password);
		existingDoctor.setSpecialization(requestDto.specialization);
		existingDoctor.setCreatedDate(existingDoctor.getCreatedDate());
		existingDoctor.setLastUpdatedDate(new Date(System.currentTimeMillis()));
		existingDoctor.setUsername(username);;
		existingDoctor.setRole(existingDoctor.getRole());
		doctorRepository.save(existingDoctor);
		return "Doctor Profile Updated Successfully";
	}

	@Transactional
	public String deleteDoctor(String username)
	{
		Doctor ExistingDoctor= doctorRepository.findByUsername(username);
		if(ExistingDoctor == null)
		{
			return null;
		}
		doctorRepository.deleteByUsername(username);
		return "Doctor Data Deleted Successfully";
	}
}
