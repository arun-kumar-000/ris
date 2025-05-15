package com.live.ris.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.live.ris.entities.InvestigationBillEntry;

public interface InvestigationBillEntryRepository extends JpaRepository<InvestigationBillEntry, Integer> {
}
