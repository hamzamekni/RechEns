package com.example.resens.service;

import com.example.resens.model.Ville;

import java.util.List;

public interface VilleService {
    List<Ville> getAllVilles();
    Ville getVilleById(Long id);
    Ville saveVille(Ville ville);
    Ville updateVille(Long id, Ville updatedVille);
    void deleteVilleById(Long id);
}
