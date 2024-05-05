package com.example.resens.service;

import com.example.resens.model.DemandeDeCour;

import java.util.List;

public interface DemandeDeCourService {
    DemandeDeCour saveDemandeDeCour(DemandeDeCour demandeDeCour);
    DemandeDeCour updateDemandeDeCour(Long id, DemandeDeCour updatedDemandeDeCour);
    void deleteDemandeDeCour(Long id);
    DemandeDeCour getDemandeDeCourById(Long id);
    List<DemandeDeCour> getAllDemandeDeCours();
}
