package com.example.resens.service;

import com.example.resens.model.Paiement;

import java.util.List;

public interface PaiementService {
    Paiement savePaiement(Paiement paiement);
    Paiement updatePaiement(Long id, Paiement updatedPaiement);
    void deletePaiement(Long id);
    Paiement getPaiementById(Long id);
    List<Paiement> getAllPaiements();
}
