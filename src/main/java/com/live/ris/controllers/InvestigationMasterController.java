package com.live.ris.controllers;

import com.live.ris.entities.InvestigationMaster;
import com.live.ris.services.InvestigationMasterService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/investigations")
public class InvestigationMasterController {

    @Autowired
    private InvestigationMasterService service;

    @GetMapping
    public String viewAll(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            model.addAttribute("investigations", service.search(keyword));
        } else {
            model.addAttribute("investigations", service.getAll());
        }
        return "investigation_master_list";
    }
    
    @GetMapping("/search")
    @ResponseBody
    public List<InvestigationMaster> getInvestigations(@RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return service.search(keyword);
        } else {
            return service.getAll();
        }
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("investigation", new InvestigationMaster());
        model.addAttribute("labNames", service.getAll().stream()
                .map(InvestigationMaster::getInvLab)
                .distinct()
                .sorted()
                .toList());
        return "investigation_master_form";
    }


    @PostMapping("/save")
    public String saveInvestigation(@ModelAttribute InvestigationMaster investigation) {
        service.save(investigation);
        return "redirect:/investigations";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        service.getById(id).ifPresent(investigation -> model.addAttribute("investigation", investigation));
        return "investigation_master_form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/investigations";
    }
}
