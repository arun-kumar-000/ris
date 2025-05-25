package com.live.ris.controllers;

import com.live.ris.entities.Patient;
import com.live.ris.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                patient.setEntryDatetime(existingPatient.getEntryDatetime());
            }
        } else {
            patient.setEntryDatetime(LocalDateTime.now());
        }

        patient.setActive(1);
        patientService.savePatient(patient);
        return "redirect:/patients?success=true";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Map<String, Object>> searchPatientsCombo(@RequestParam String keyword) {
        List<Patient> patients = patientService.searchPatients(keyword);
        return patients.stream()
                .limit(10)
                .map(p -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("pid", p.getPid());
                    map.put("pName", p.getPName());
                    return map;
                })
                .collect(Collectors.toList());
    }
    @GetMapping("/investigation_entry")
    public String showSearchForm() {
        return "investigation_entry";
    }
    @GetMapping("/details/{pid}")
    @ResponseBody
    public ResponseEntity<Patient> getPatientDetails(@PathVariable Long pid) {
        Patient patient = patientService.getPatientById(pid);
        return patient != null ? ResponseEntity.ok(patient) : ResponseEntity.notFound().build();
    }
    
    @GetMapping("/reports_gen")
    public String showReportForm() {
        return "report_generate";
    }
    
    @GetMapping("/reports/generate")
    public String generateReport(@RequestParam("pid") Long pid, Model model) {
        Patient patient = patientService.getPatientById(pid);
        model.addAttribute("patient", patient);
        return "report_editor"; // Thymeleaf template to show report generation form
    }

}
