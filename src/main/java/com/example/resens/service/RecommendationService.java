package com.example.resens.service;

import com.example.resens.model.Recommendation;

import java.util.List;

public interface RecommendationService {
    Recommendation saveRecommendation(Recommendation recommendation, Long etudiantId, Long teacherId);
    Recommendation updateRecommendation(Long id, Recommendation updatedRecommendation);
    void deleteRecommendation(Long id);
    Recommendation getRecommendationById(Long id);
    List<Recommendation> getAllRecommendations();
}
