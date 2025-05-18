package com.live.ris.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.live.ris.entities.InvestigationBillEntry;
import com.live.ris.services.InvestigationBillEntryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inv_bill_entry")
public class InvestigationBillEntryController {

    @Autowired
    private InvestigationBillEntryService service;

    @GetMapping
    public List<InvestigationBillEntry> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<InvestigationBillEntry> getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public InvestigationBillEntry create(@RequestBody InvestigationBillEntry entry) {
        return service.save(entry);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
