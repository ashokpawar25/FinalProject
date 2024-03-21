package com.medrecord.Service;

import java.sql.Date;
import java.util.List;

import com.medrecord.requestdto.UpdateDoctorRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.medrecord.Entity.Doctor;
import com.medrecord.dao.DoctorRepositery;
import com.medrecord.requestdto.DoctorRegisterRequestDto;

@Component
public class DoctorServices 
{
	@Autowired
	DoctorRepositery doctorRepositery;
	
	public List<Doctor> getDoctors() 
	{
		List<Doctor> doctors=doctorRepositery.findAll();
		return doctors;
	}
	
	public String registerDoctor(DoctorRegisterRequestDto requestDto)
	{
		Doctor existingUsername = doctorRepositery.findByUsername(requestDto.username);
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
		doctor.setRole(requestDto.role);
		doctor.setCreatedDate(new Date(System.currentTimeMillis()));
		doctor.setLastUpdatedDate(new Date(System.currentTimeMillis()));
		
		doctorRepositery.save(doctor);
		return "Doctor Registered Successfully";
	}
	
	public String updateDoctor(String username, UpdateDoctorRequestDto requestDto)
	{
		Doctor existingDoctor = doctorRepositery.findByUsername(username);
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
		doctorRepositery.save(existingDoctor);
		return "Doctor Profile Updated Successfully";
	}
	
	public String deleteDoctor(String username)
	{
		Doctor ExistingDoctor= doctorRepositery.findByUsername(username);
		if(ExistingDoctor == null)
		{
			return null;
		}
		doctorRepositery.deleteByUsername(username);
		return "Doctor Data Deleted Successfully";
	}
}
