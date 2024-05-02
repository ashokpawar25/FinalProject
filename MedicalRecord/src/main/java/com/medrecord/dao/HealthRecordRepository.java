package com.medrecord.dao;

import com.medrecord.Entity.HealthRecord;
import com.medrecord.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRecordRepository extends JpaRepository<HealthRecord,Integer> {
    HealthRecord findByPatient(Patient patient);
}
