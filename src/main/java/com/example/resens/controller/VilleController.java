package com.example.resens.controller;

import com.example.resens.model.Ville;
import com.example.resens.service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/villes")
public class VilleController {

    @Autowired
    private VilleService villeService;

    @GetMapping
    public List<Ville> getAllVilles() {
        return villeService.getAllVilles();
    }

    @GetMapping("/{id}")
    public Ville getVilleById(@PathVariable Long id) {
        return villeService.getVilleById(id);
    }

    @PostMapping
    public Ville saveVille(@RequestBody Ville ville) {
        return villeService.saveVille(ville);
    }

    @PutMapping("/{id}")
    public Ville updateVille(@PathVariable Long id, @RequestBody Ville updatedVille) {
        return villeService.updateVille(id, updatedVille);
    }

    @DeleteMapping("/{id}")
    public void deleteVilleById(@PathVariable Long id) {
        villeService.deleteVilleById(id);
    }
}
