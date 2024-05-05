package com.example.resens.service;

import com.example.resens.model.Matiere;

import java.util.List;

public interface MatiereService {
    List<Matiere> getAllMatieres();
    Matiere getMatiereById(Long id);
    Matiere saveMatiere(Matiere matiere);
    void deleteMatiereById(Long id);
}
