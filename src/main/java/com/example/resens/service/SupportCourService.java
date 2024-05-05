package com.example.resens.service;

import com.example.resens.model.SupportCour;

import java.util.List;

public interface SupportCourService {
    SupportCour saveSupportCour(SupportCour supportCour);
    SupportCour updateSupportCour(Long id, SupportCour updatedSupportCour);
    void deleteSupportCour(Long id);
    SupportCour getSupportCourById(Long id);
    List<SupportCour> getAllSupportCours();
}
