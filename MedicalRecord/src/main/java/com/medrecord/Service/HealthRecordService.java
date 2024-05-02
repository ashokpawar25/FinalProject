package com.medrecord.Service;

import com.medrecord.Entity.Doctor;
import com.medrecord.Entity.HealthRecord;
import com.medrecord.Entity.Patient;
import com.medrecord.Entity.UpdateEntry;
import com.medrecord.dao.DoctorRepository;
import com.medrecord.dao.HealthRecordRepository;
import com.medrecord.dao.PatientRepository;
import com.medrecord.dao.UpdateEntryRepository;
import com.medrecord.requestdto.CreateUpdateEntryRequestDto;
import com.medrecord.responsedto.GetHealthRecordResponseDto;
import com.medrecord.responsedto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HealthRecordService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    HealthRecordRepository healthRecordRepository;
    @Autowired
    UpdateEntryRepository updateEntryRepository;

    public ServiceResponse createHealthRecord(String patientUsername) {
        Patient patient = patientRepository.findByUsername(patientUsername);
        if (patient == null)
            return new ServiceResponse(false, "Patient username is incorrect");
        HealthRecord existingHealthRecord = healthRecordRepository.findByPatient(patient);
        if (existingHealthRecord != null)
            return new ServiceResponse(false, "Health record for the patient is already present");
        HealthRecord healthRecord = new HealthRecord(new ArrayList<>(), LocalDate.now(), LocalDate.now(), patient);
        healthRecordRepository.save(healthRecord);
        return new ServiceResponse(true, "Health record created successfully");
    }

    public ServiceResponse createNewEntry(CreateUpdateEntryRequestDto requestDto) {
        String doctorUsername = requestDto.doctorUsername;
        Doctor existingDoctor = doctorRepository.findByUsername(doctorUsername);
        if (existingDoctor == null) return new ServiceResponse(false, "Invalid doctor username");
        Patient existingPatient = patientRepository.findByUsername(requestDto.patientUsername);
        if (existingPatient == null) return new ServiceResponse(false, "Invalid patient username");
        HealthRecord existingHealthRecord = healthRecordRepository.findByPatient(existingPatient);
        if (existingHealthRecord == null) return new ServiceResponse(false, "Health record not found for patient");
        UpdateEntry updateEntry = new UpdateEntry(requestDto.medicine, requestDto.doseAndFrequency, requestDto.symptoms, requestDto.healthGoals, requestDto.notes, LocalDate.now(), LocalDate.now(), existingDoctor, existingHealthRecord);
        updateEntryRepository.save(updateEntry);
        return new ServiceResponse(true, "Entry added successfully into Health record");
    }

    public List<GetHealthRecordResponseDto> getHealthRecords(String patientUsername) {
        Patient patient = patientRepository.findByUsername(patientUsername);
        if (patient == null) return null;
        HealthRecord existingHealthRecord = healthRecordRepository.findByPatient(patient);
        if (existingHealthRecord == null) return null;
        List<UpdateEntry> records = updateEntryRepository.findByHealthRecord(existingHealthRecord);
        List<GetHealthRecordResponseDto> recordsToReturn = new ArrayList<>();
        for (UpdateEntry entry : records) {
            GetHealthRecordResponseDto healthRecord = new GetHealthRecordResponseDto(entry.getId(), entry.getMedicine(), entry.getDoseAndFrequency(), entry.getSymptoms(), entry.getHealthGoals(), entry.getNotes(), entry.getCreatedDate(), entry.getLastUpdatedDate(), entry.getDoctor().getUsername());
            recordsToReturn.add(healthRecord);
        }
        return recordsToReturn;
    }
}
