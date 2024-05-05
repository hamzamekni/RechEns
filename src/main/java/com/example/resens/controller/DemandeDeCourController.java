package com.example.resens.controller;

import com.example.resens.model.DemandeDeCour;
import com.example.resens.service.DemandeDeCourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demandeDeCours")
public class DemandeDeCourController {

    @Autowired
    private DemandeDeCourService demandeDeCourService;

    @PostMapping
    public DemandeDeCour saveDemandeDeCour(@RequestBody DemandeDeCour demandeDeCour) {
        return demandeDeCourService.saveDemandeDeCour(demandeDeCour);
    }

    @PutMapping("/{id}")
    public DemandeDeCour updateDemandeDeCour(@PathVariable Long id, @RequestBody DemandeDeCour updatedDemandeDeCour) {
        return demandeDeCourService.updateDemandeDeCour(id, updatedDemandeDeCour);
    }

    @DeleteMapping("/{id}")
    public void deleteDemandeDeCour(@PathVariable Long id) {
        demandeDeCourService.deleteDemandeDeCour(id);
    }

    @GetMapping("/{id}")
    public DemandeDeCour getDemandeDeCourById(@PathVariable Long id) {
        return demandeDeCourService.getDemandeDeCourById(id);
    }

    @GetMapping
    public List<DemandeDeCour> getAllDemandeDeCours() {
        return demandeDeCourService.getAllDemandeDeCours();
    }
}