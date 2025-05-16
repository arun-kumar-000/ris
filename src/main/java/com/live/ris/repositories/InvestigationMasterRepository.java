package com.live.ris.repositories;

import com.live.ris.entities.InvestigationMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestigationMasterRepository extends JpaRepository<InvestigationMaster, Integer> {
    List<InvestigationMaster> findByInvNameContainingIgnoreCaseOrInvCodeContainingIgnoreCase(String name, String code);
}
