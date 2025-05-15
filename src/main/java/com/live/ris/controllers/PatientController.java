package com.live.ris.controllers;

import com.live.ris.entities.Patient;
import com.live.ris.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient_register";
    }

    @PostMapping("/register")
    public String registerPatient(@ModelAttribute Patient patient) {
        if (patient.getPid() == null) {
            patient.setEntryDatetime(LocalDateTime.now());
        }
        patient.setActive(1);
        patientService.savePatient(patient);
        return "redirect:/patients/list";
    }

    @GetMapping("/list")
    public String viewPatients(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Patient> patients;
        if (keyword != null && !keyword.trim().isEmpty()) {
            patients = patientService.searchPatients(keyword.trim());
        } else {
            patients = patientService.getAllPatients();
        }
        model.addAttribute("patients", patients);
        model.addAttribute("keyword", keyword);
        return "patient_list";
    }

    @GetMapping("/edit/{id}")
    public String editPatient(@PathVariable("id") Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient == null) {
            return "redirect:/patients/list";
        }
        model.addAttribute("patient", patient);
        return "patient_register";
    }
    
    @PostMapping("/patients/register")
    public String savePatient(@ModelAttribute("patient") Patient patient) {
        if (patient.getPid() != null) {
            Patient existingPatient = patientService.getPatientById(patient.getPid());
            if (existingPatient != null) {
                // Preserve fields that shouldn't be changed by the form (e.g., entryDatetime)
                patient.setEntryDatetime(existingPatient.getEntryDatetime());
            }
        } else {
            patient.setEntryDatetime(LocalDateTime.now());
        }

        patient.setActive(1); // if always active
        patientService.savePatient(patient);
        return "redirect:/patients?success=true";
    }

}
