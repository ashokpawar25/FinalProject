package com.medrecord.controller;

import com.medrecord.Service.HealthRecordService;
import com.medrecord.requestdto.CreateUpdateEntryRequestDto;
import com.medrecord.responsedto.GetHealthRecordResponseDto;
import com.medrecord.responsedto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HealthRecordController {
    @Autowired
    HealthRecordService healthRecordService;

    @PostMapping("/create-health-record")
    public ResponseEntity<String> createHealthRecord(@RequestParam String patientUsername) {
        ServiceResponse response = healthRecordService.createHealthRecord(patientUsername);
        if (!response.status) {
            return new ResponseEntity<>(response.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(response.getMessage());
    }

    @GetMapping("/get-health-record")
    public ResponseEntity<List<GetHealthRecordResponseDto>> getHealthRecords(@RequestParam String patientUsername) {
        List<GetHealthRecordResponseDto> records = healthRecordService.getHealthRecords(patientUsername);
        return ResponseEntity.ok(records);
    }

    @PostMapping("/create-update-entry")
    public ResponseEntity<String> createEntryOfHealthRecord(@RequestBody CreateUpdateEntryRequestDto requestDto) {
        ServiceResponse response = healthRecordService.createNewEntry(requestDto);
        if (!response.status) return new ResponseEntity<>(response.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(response.getMessage());
    }
}
