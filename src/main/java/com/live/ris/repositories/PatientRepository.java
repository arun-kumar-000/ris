package com.live.ris.repositories;

import com.live.ris.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
   
	@Query("SELECT p FROM Patient p WHERE " +
            "LOWER(p.pName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.telephone) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.aadhaar) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(p.pid AS string) LIKE %:keyword%")
     List<Patient> searchPatients(@Param("keyword") String keyword);
}