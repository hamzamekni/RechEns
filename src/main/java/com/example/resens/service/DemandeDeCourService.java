package com.example.resens.service;

import com.example.resens.model.DemandeDeCour;

import java.util.List;

public interface DemandeDeCourService {
    DemandeDeCour saveDemandeDeCour(DemandeDeCour demandeDeCour, Long adress_id, Long matiere_id,
                                    Long etudiant_id,
                                    Long teacherId);
    DemandeDeCour updateDemandeDeCour(Long id, DemandeDeCour updatedDemandeDeCour);
    void deleteDemandeDeCour(Long id);
    DemandeDeCour getDemandeDeCourById(Long id);
    List<DemandeDeCour> getAllDemandeDeCours();
}
