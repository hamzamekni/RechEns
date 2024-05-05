package com.example.resens.service;

import com.example.resens.model.Etudiant;

import java.util.List;

public interface EtudiantService {
    Etudiant saveEtudiant(Etudiant etudiant);
    Etudiant updateEtudiant(Long id, Etudiant updatedEtudiant);
    void deleteEtudiant(Long id);
    Etudiant getEtudiantById(Long id);
    List<Etudiant> getAllEtudiants();
}
