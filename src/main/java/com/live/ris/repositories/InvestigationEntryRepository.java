package com.live.ris.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.live.ris.entities.InvestigationEntry;

public interface InvestigationEntryRepository extends JpaRepository<InvestigationEntry, Integer> {
}
