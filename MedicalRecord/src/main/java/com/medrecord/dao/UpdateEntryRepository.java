package com.medrecord.dao;

import com.medrecord.Entity.HealthRecord;
import com.medrecord.Entity.UpdateEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UpdateEntryRepository extends JpaRepository<UpdateEntry,Integer> {
    List<UpdateEntry> findByHealthRecord(HealthRecord existingHealthRecord);
}
