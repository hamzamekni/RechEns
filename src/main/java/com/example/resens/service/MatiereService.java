package com.example.resens.service;

import com.example.resens.model.Matiere;
import com.example.resens.model.NiveauEtude;

import java.util.List;

public interface MatiereService {
    List<Matiere> getAllMatieres();
    Matiere getMatiereById(Long id);
    Matiere saveMatiere(Matiere matiere, Long niveau_etude_id);
    void deleteMatiereById(Long id);
    Matiere updateMatiere(Long id, Matiere updatedMatiere);
}
