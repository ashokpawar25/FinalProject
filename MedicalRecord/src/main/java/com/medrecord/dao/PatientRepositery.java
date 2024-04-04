package com.medrecord.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medrecord.Entity.Patient;


public interface PatientRepositery extends JpaRepository<Patient, String>
{
	public Patient findByUsername(String username);
}
