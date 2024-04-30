package com.medrecord.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medrecord.Entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String>
{
	public Doctor findByUsername(String username);
	public void deleteByUsername(String username);
}
