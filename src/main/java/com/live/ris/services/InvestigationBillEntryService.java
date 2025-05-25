package com.live.ris.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.live.ris.entities.InvestigationBillEntry;
import com.live.ris.repositories.InvestigationBillEntryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InvestigationBillEntryService {

    @Autowired
    private InvestigationBillEntryRepository repository;

    public List<InvestigationBillEntry> getAll() {
        return repository.findAll();
    }

    public Optional<InvestigationBillEntry> getById(int id) {
        return repository.findById(id);
    }

    public InvestigationBillEntry save(InvestigationBillEntry entry) {
        return repository.save(entry);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
    
    
}
