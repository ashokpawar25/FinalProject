package com.medrecord.dao;

import com.medrecord.Entity.Doctor;
import com.medrecord.Entity.Patient;
import com.medrecord.Entity.PhrRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhrRequestRepository extends JpaRepository<PhrRequest, Integer> {
    PhrRequest findByDoctorAndPatient(Doctor doctor, Patient patient);
}
