package com.example.resens.controller;

import com.example.resens.model.SupportCour;
import com.example.resens.service.SupportCourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/support-cours")
public class SupportCourController {

    @Autowired
    private SupportCourService supportCourService;

    @GetMapping
    public List<SupportCour> getAllSupportCours() {
        return supportCourService.getAllSupportCours();
    }

    @GetMapping("/{id}")
    public SupportCour getSupportCourById(@PathVariable Long id) {
        return supportCourService.getSupportCourById(id);
    }

    @PostMapping
    public SupportCour saveSupportCour(@RequestBody SupportCour supportCour) {
        return supportCourService.saveSupportCour(supportCour);
    }

    @PutMapping("/{id}")
    public SupportCour updateSupportCour(@PathVariable Long id, @RequestBody SupportCour updatedSupportCour) {
        return supportCourService.updateSupportCour(id, updatedSupportCour);
    }

    @DeleteMapping("/{id}")
    public void deleteSupportCour(@PathVariable Long id) {
        supportCourService.deleteSupportCour(id);
    }
}
