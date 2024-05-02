package com.medrecord.Service;

import com.medrecord.Entity.Doctor;
import com.medrecord.Entity.Patient;
import com.medrecord.Entity.PhrRequest;
import com.medrecord.dao.DoctorRepository;
import com.medrecord.dao.PatientRepository;
import com.medrecord.dao.PhrRequestRepository;
import com.medrecord.responsedto.GetPhrRequestsResponseDto;
import com.medrecord.responsedto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhrRequestService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    PhrRequestRepository phrRequestRepository;
    public ServiceResponse createPhrRequest(String doctorUsername, String patientUsername) {
        Doctor existingDoctor = doctorRepository.findByUsername(doctorUsername);
        if(existingDoctor == null) return new ServiceResponse(false,"Invalid doctor username");
        Patient existingPatient = patientRepository.findByUsername(patientUsername);
        if(existingPatient == null) return new ServiceResponse(false,"Invalid Patient Username");
        PhrRequest existingRequest = phrRequestRepository.findByDoctorAndPatient(existingDoctor,existingPatient);
        if(existingRequest != null) return new ServiceResponse(false,"Request from this doctor is already present");
        PhrRequest phrRequest = new PhrRequest(existingDoctor,existingPatient,false, LocalDate.now(),LocalDate.now());
        phrRequestRepository.save(phrRequest);
        return new ServiceResponse(true,"Phr request created successfully");
    }

    public List<GetPhrRequestsResponseDto> getPhrRequests() {
        List<PhrRequest> allRequests = phrRequestRepository.findAll();
        List<GetPhrRequestsResponseDto> requestsToReturn = new ArrayList<>();
        for(PhrRequest request:allRequests)
        {
            GetPhrRequestsResponseDto requestDto = new GetPhrRequestsResponseDto(request.getId(),request.getDoctor().getFirstName()+" "+request.getDoctor().getLastName(),request.getPatient().getPatientName(), request.isRequestStatus(), request.getCreatedDate(),request.getLastUpdatedDate());
            requestsToReturn.add(requestDto);
        }
        return requestsToReturn;
    }

    public ServiceResponse acceptRequest(int requestId) {
        Optional<PhrRequest> existingPhrRequest = phrRequestRepository.findById(requestId);
        if(existingPhrRequest.isPresent())
        {
            PhrRequest request = existingPhrRequest.get();
            if(request.isRequestStatus()) return new ServiceResponse(false,"Request is already accepted");
            request.setRequestStatus(true);
            phrRequestRepository.save(request);
            return new ServiceResponse(true,"Request Accepted successfully");
        }
        return new ServiceResponse(false,"Phr request not found");
    }

    public ServiceResponse rejectRequest(int requestId) {
        Optional<PhrRequest> existingPhrRequest = phrRequestRepository.findById(requestId);
        if(existingPhrRequest.isPresent())
        {
            PhrRequest request = existingPhrRequest.get();
            phrRequestRepository.delete(request);
            return new ServiceResponse(true,"Request rejected successfully");
        }
        return new ServiceResponse(false,"Phr request not found");
    }
}
