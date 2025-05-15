package com.live.ris.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/investigation-entry")
public class InvestigationEntryController {

    @Autowired
    private InvestigationEntryService service;

    @GetMapping
    public List<InvestigationEntry> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<InvestigationEntry> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public InvestigationEntry create(@RequestBody InvestigationEntry entry) {
        return service.save(entry);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
