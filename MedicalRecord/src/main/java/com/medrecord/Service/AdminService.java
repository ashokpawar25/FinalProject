package com.medrecord.Service;

import com.medrecord.Entity.Doctor;
import com.medrecord.Entity.Patient;
import com.medrecord.dao.DoctorRepositery;
import com.medrecord.dao.PatientRepositery;
import com.medrecord.responsedto.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService
{

    @Autowired
    PatientRepositery patientRepositery;

    @Autowired
    DoctorRepositery doctorRepositery;

    public ServiceResponse approvePatient(String patientUsername)
    {
        Patient patient = patientRepositery.findByUsername(patientUsername);
        if (patient == null)
            return new ServiceResponse(false,"Patient Not Found");

        patient.setAproved(true);
        patientRepositery.save(patient);
        return new ServiceResponse(true,"Patient request approved successfully");
    }

    public ServiceResponse rejectPatient(String patientUsername)
    {
        Patient patient = patientRepositery.findByUsername(patientUsername);
        if (patient == null)
            return new ServiceResponse(false,"Patient Not Found");

        patientRepositery.delete(patient);
        return new ServiceResponse(true,"Patient request rejected successfully");
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
