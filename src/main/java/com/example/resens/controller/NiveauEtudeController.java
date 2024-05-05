package com.example.resens.controller;


import com.example.resens.model.NiveauEtude;
import com.example.resens.service.NiveauEtudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/niveauEtudes")
public class NiveauEtudeController {

    @Autowired
    private NiveauEtudeService niveauEtudeService;

    @GetMapping
    public List<NiveauEtude> getAllNiveauEtudes() {
        return niveauEtudeService.getAllNiveauEtudes();
    }

    @GetMapping("/{id}")
    public NiveauEtude getNiveauEtudeById(@PathVariable Long id) {
        return niveauEtudeService.getNiveauEtudeById(id);
    }

    @PostMapping
    public NiveauEtude saveNiveauEtude(@RequestBody NiveauEtude niveauEtude) {
        return niveauEtudeService.saveNiveauEtude(niveauEtude);
    }

    @PutMapping("/{id}")
    public NiveauEtude updateNiveauEtude(@PathVariable Long id, @RequestBody NiveauEtude updatedNiveauEtude) {
        return niveauEtudeService.updateNiveauEtude(id, updatedNiveauEtude);
    }

    @DeleteMapping("/{id}")
    public void deleteNiveauEtude(@PathVariable Long id) {
        niveauEtudeService.deleteNiveauEtude(id);
    }
}
