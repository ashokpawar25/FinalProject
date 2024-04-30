package com.medrecord.controller;

import com.medrecord.Entity.ApproveRequest;
import com.medrecord.Service.AdminService;
import com.medrecord.responsedto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController
{
    @Autowired
    AdminService adminService;

    @GetMapping("/getApproveRequests")
    public ResponseEntity<List<ApproveRequest>> getApproveRequests()
    {
        return ResponseEntity.ok(adminService.getApproveRequests());

    }

    @PutMapping("/approveRequest/{requestId}")
    public ResponseEntity<String> approveRequest(@PathVariable("requestId")int requestId)
    {
        ServiceResponse response = adminService.approveRequest(requestId);
        if(response.status)
            return ResponseEntity.ok(response.message);

        return new ResponseEntity<>(response.message,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/rejectRequest/{requestId}")
    public ResponseEntity<String> rejectRequest(@PathVariable("requestId")int requestId)
    {
        ServiceResponse response = adminService.rejectRequest(requestId);
        if(response.status)
            return ResponseEntity.ok(response.message);

        return new ResponseEntity<>(response.message,HttpStatus.NOT_FOUND);
    }
}
