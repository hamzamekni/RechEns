package com.example.resens.controller;

import com.example.resens.model.Paiement;
import com.example.resens.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paiements")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;

    @GetMapping
    public List<Paiement> getAllPaiements() {
        return paiementService.getAllPaiements();
    }

    @GetMapping("/{id}")
    public Paiement getPaiementById(@PathVariable Long id) {
        return paiementService.getPaiementById(id);
    }

    @PostMapping
    public Paiement savePaiement(@RequestBody Paiement paiement,
                                 @RequestParam Long etudiantId,
                                 @RequestParam Long teacherId) {
        return paiementService.savePaiement(paiement,etudiantId,teacherId);
    }

    @PutMapping("/{id}")
    public Paiement updatePaiement(@PathVariable Long id, @RequestBody Paiement updatedPaiement) {
        return paiementService.updatePaiement(id, updatedPaiement);
    }

    @DeleteMapping("/{id}")
    public void deletePaiement(@PathVariable Long id) {
        paiementService.deletePaiement(id);
    }
}