package com.live.ris.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.live.ris.entities.InvestigationBillEntry;
import com.live.ris.entities.InvestigationEntry;

public interface InvestigationEntryRepository extends JpaRepository<InvestigationEntry, Integer> {
	 @Query("SELECT e FROM InvestigationEntry e WHERE " +
	           "(e.pName LIKE %:keyword% OR e.pId LIKE %:keyword% OR e.invReceiptId LIKE %:keyword%) " +
	           "")
	    List<InvestigationEntry> searchToday(String keyword);

	    @Query("SELECT e FROM InvestigationEntry e WHERE DATE(e.invDateTime) = CURRENT_DATE")
	    List<InvestigationEntry> findTodayEntries();


}
