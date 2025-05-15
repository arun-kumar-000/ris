package com.live.ris.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.live.ris.entities.InvestigationEntry;
import com.live.ris.repositories.InvestigationEntryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InvestigationEntryService {

    @Autowired
    private InvestigationEntryRepository repository;

    public List<InvestigationEntry> getAll() {
        return repository.findAll();
    }

    public Optional<InvestigationEntry> getById(int id) {
        return repository.findById(id);
    }

    public InvestigationEntry save(InvestigationEntry entry) {
        return repository.save(entry);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
