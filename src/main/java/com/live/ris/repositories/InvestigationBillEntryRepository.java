package com.live.ris.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.live.ris.entities.InvestigationBillEntry;

public interface InvestigationBillEntryRepository extends JpaRepository<InvestigationBillEntry, Integer> {
}
