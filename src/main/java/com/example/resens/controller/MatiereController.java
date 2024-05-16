package com.example.resens.controller;

import com.example.resens.model.Matiere;
import com.example.resens.model.NiveauEtude;
import com.example.resens.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matieres")
public class MatiereController {

    @Autowired
    private MatiereService matiereService;

    @GetMapping
    public List<Matiere> getAllMatieres() {
        return matiereService.getAllMatieres();
    }

    @GetMapping("/{id}")
    public Matiere getMatiereById(@PathVariable Long id) {
        return matiereService.getMatiereById(id);
    }

    @PostMapping
    public Matiere saveMatiere(@RequestBody Matiere matiere,@RequestParam Long niveau_etude_id) {
        return matiereService.saveMatiere(matiere, niveau_etude_id);
    }
    @PutMapping("/{id}")
    public Matiere updateMatiere(@PathVariable Long id, @RequestBody Matiere updatedMatiere) {
        return matiereService.updateMatiere(id, updatedMatiere);
    }
    @DeleteMapping("/{id}")
    public void deleteMatiereById(@PathVariable Long id) {
        matiereService.deleteMatiereById(id);
    }
}
