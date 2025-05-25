package com.live.ris.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.live.ris.dto.InvestigationEntryRequest;
import com.live.ris.entities.InvestigationEntry;
import com.live.ris.services.InvestigationEntryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
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
    
    @GetMapping("/list")
    public String listEntries(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("entries", service.getTodayEntries(keyword));
        model.addAttribute("keyword", keyword);
        return "investigation_entry_list";  // This tells Thymeleaf to load the template investigation_entry_list.html
    }

    @GetMapping("/reportlist")
    public String reportlistEntries(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("entries", service.getTodayEntries(keyword));
        model.addAttribute("keyword", keyword);
        return "report_generate";  // This tells Thymeleaf to load the template investigation_entry_list.html
    }

    // Cancel investigation entry and redirect back to list
    @PostMapping("/cancel/{id}")
    public String cancelEntry(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        service.cancelEntry(id);
        redirectAttributes.addFlashAttribute("message", "Entry canceled successfully.");
        return "redirect:/inv_entry/list";
    }
}
