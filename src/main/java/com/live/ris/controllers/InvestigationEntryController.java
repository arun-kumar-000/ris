package com.live.ris.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.live.ris.dto.InvestigationEntryRequest;
import com.live.ris.entities.InvestigationEntry;
import com.live.ris.services.InvestigationEntryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/inv_entry")
public class InvestigationEntryController {

    @Autowired
    private InvestigationEntryService service;

    @GetMapping
    public List<InvestigationEntry> getAll() {
        return service.getAll();//test
    }

    @GetMapping("/{id}")
    public Optional<InvestigationEntry> getById(@PathVariable int id) {
        return service.getById(id);
    }
    
    @PostMapping("/entry")
    public ResponseEntity<Map<String, Object>> saveInvestigationEntry(@RequestBody InvestigationEntryRequest request) {
        String receiptId = service.saveInvestigationWithBilling(request);
        Map<String, Object> response = new HashMap<>();
        response.put("receiptId", receiptId);
        return ResponseEntity.ok(response);
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
