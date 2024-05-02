package com.medrecord.controller;

import com.medrecord.Entity.PhrRequest;
import com.medrecord.Service.PhrRequestService;
import com.medrecord.responsedto.GetPhrRequestsResponseDto;
import com.medrecord.responsedto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhrRequestController {
    @Autowired
    PhrRequestService phrRequestService;
    @PostMapping("/request-phr")
    public ResponseEntity<String> requestPhr(@RequestParam String doctorUsername,@RequestParam String patientUsername)
    {
        ServiceResponse response = phrRequestService.createPhrRequest(doctorUsername, patientUsername);
        if(!response.status) return new ResponseEntity<>(response.message,HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(response.message);
    }

    @GetMapping("/get-phr-requests")
    public ResponseEntity<List<GetPhrRequestsResponseDto>> getRequests()
    {
         List<GetPhrRequestsResponseDto> requests = phrRequestService.getPhrRequests();
         return ResponseEntity.ok(requests);
    }

    @PutMapping("/accept-phr-request/{requestId}")
    public ResponseEntity<String> acceptPhrRequest(@PathVariable int requestId)
    {
        ServiceResponse response = phrRequestService.acceptRequest(requestId);
        if(!response.status) return new ResponseEntity<>(response.message,HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(response.message);
    }

    @DeleteMapping("/reject-phr-request/{requestId}")
    public ResponseEntity<String> rejectRequest(@PathVariable int requestId)
    {
        ServiceResponse response = phrRequestService.rejectRequest(requestId);
        if(!response.status) return new ResponseEntity<>(response.message,HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(response.message);
    }

}
