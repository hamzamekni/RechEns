package com.example.resens.service;

import com.example.resens.dto.DemandeRequest;
import com.example.resens.enumeration.Statut_Demande;
import com.example.resens.model.*;

import java.util.List;
import java.util.Optional;

public interface DemandeDeCourService {
    DemandeDeCour saveDemandeDeCour(DemandeDeCour demandeDeCour, Long adress_id, Long matiere_id,
                                    Long etudiant_id,
                                    Long teacherId);
    DemandeDeCour updateDemandeDeCour(Long id, DemandeDeCour updatedDemandeDeCour);
    void deleteDemandeDeCour(Long id);
    DemandeDeCour getDemandeDeCourById(Long id);
    List<DemandeDeCour> getAllDemandeDeCours();


    void demadeForm(DemandeRequest demandeRequest, Statut_Demande statutDemande,
                    Long teacher, Long etudiant, Long matiere, Long adress);
}
