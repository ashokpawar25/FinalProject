package com.medrecord.Service;

import com.medrecord.Entity.ApproveRequest;
import com.medrecord.Entity.Doctor;
import com.medrecord.Entity.Patient;
import com.medrecord.dao.ApproveRequestRepository;
import com.medrecord.dao.DoctorRepository;
import com.medrecord.dao.PatientRepository;
import com.medrecord.responsedto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService
{

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    ApproveRequestRepository approveRequestRepository;

    public List<ApproveRequest> getApproveRequests()
    {
        return approveRequestRepository.findAll();
    }

    public ServiceResponse approveRequest(int requestId)
    {
        Optional<ApproveRequest> request = approveRequestRepository.findById(requestId);
        if(request.isPresent())
        {
            ApproveRequest request1 = request.get();
            if(request1.getRole().equalsIgnoreCase("patient"))
            {
                Patient patient = patientRepository.findByUsername(request1.getUsername());
                patient.setApproved(true);
                patientRepository.save(patient);
                approveRequestRepository.deleteById(request1.getId());
                return new ServiceResponse(true,"User request approved successfully");
            }
            if(request1.getRole().equalsIgnoreCase("doctor"))
            {
                Doctor doctor = doctorRepository.findByUsername(request1.getUsername());
//                doctor.setApproved(true);
//                doctorRepository.save(doctor);
                return new ServiceResponse(true,"User request approved successfully");
            }
        }
        return new ServiceResponse(false,"request Not Found");
    }

    public ServiceResponse rejectRequest(int requestId)
    {
        Optional<ApproveRequest> request = approveRequestRepository.findById(requestId);
        if(request.isPresent())
        {
            ApproveRequest request1 = request.get();
            if(request1.getRole().equalsIgnoreCase("patient"))
            {
                Patient patient = patientRepository.findByUsername(request1.getUsername());
                patientRepository.delete(patient);
                approveRequestRepository.deleteById(request1.getId());
                return new ServiceResponse(true,"User request rejected successfully");
            }
            if(request1.getRole().equalsIgnoreCase("doctor"))
            {
                Doctor doctor = doctorRepository.findByUsername(request1.getUsername());
//                doctor.setApproved(true);
//                doctorRepository.save(doctor);
                approveRequestRepository.deleteById(request1.getId());
                return new ServiceResponse(true,"User request rejected successfully");
            }
        }
        return new ServiceResponse(false,"request Not Found");
    }


//    public ServiceResponse approveDoctor(String doctorUsername)
//    {
//        Doctor doctor = doctorRepositery.findByUsername(doctorUsername);
//        if (doctor == null)
//            return new ServiceResponse(false,"Doctor Not Found");
//
//        doctor.(true);
//        patientRepositery.save(patient);
//        return new ServiceResponse(true,"Patient request approved successfully");
//    }
//
//    public ServiceResponse rejectDoctor(String patientUsername)
//    {
//        Patient patient = patientRepositery.findByUsername(patientUsername);
//        if (patient == null)
//            return new ServiceResponse(false,"Patient Not Found");
//
//        patientRepositery.delete(patient);
//        return new ServiceResponse(true,"Patient request rejected successfully");
//    }
}
