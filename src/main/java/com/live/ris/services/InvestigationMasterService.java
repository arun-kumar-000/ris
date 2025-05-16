package com.live.ris.services;

import com.live.ris.entities.InvestigationMaster;

import java.util.List;
import java.util.Optional;

public interface InvestigationMasterService {
    InvestigationMaster save(InvestigationMaster investigation);
    List<InvestigationMaster> getAll();
    Optional<InvestigationMaster> getById(Integer id);
    void deleteById(Integer id);
    List<InvestigationMaster> search(String keyword);
}
