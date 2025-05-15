package com.live.ris.services;

import com.live.ris.entities.Patient;
import com.live.ris.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long pid) {
        return patientRepository.findById(pid).orElse(null);
    }

    public List<Patient> searchPatients(String keyword) {
        return patientRepository.searchPatients(keyword);
    }
}
