package com.example.resens.service;

import com.example.resens.model.NiveauEtude;

import java.util.List;

public interface NiveauEtudeService {
    NiveauEtude saveNiveauEtude(NiveauEtude niveauEtude);
    NiveauEtude updateNiveauEtude(Long id, NiveauEtude updatedNiveauEtude);
    void deleteNiveauEtude(Long id);
    NiveauEtude getNiveauEtudeById(Long id);
    List<NiveauEtude> getAllNiveauEtudes();
}
