package com.example.resens.controller;

import com.example.resens.model.Recommendation;
import com.example.resens.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping
    public List<Recommendation> getAllRecommendations() {
        return recommendationService.getAllRecommendations();
    }

    @GetMapping("/{id}")
    public Recommendation getRecommendationById(@PathVariable Long id) {
        return recommendationService.getRecommendationById(id);
    }

    @PostMapping
    public Recommendation saveRecommendation(@RequestBody Recommendation recommendation) {
        return recommendationService.saveRecommendation(recommendation);
    }

    @PutMapping("/{id}")
    public Recommendation updateRecommendation(@PathVariable Long id, @RequestBody Recommendation updatedRecommendation) {
        return recommendationService.updateRecommendation(id, updatedRecommendation);
    }

    @DeleteMapping("/{id}")
    public void deleteRecommendation(@PathVariable Long id) {
        recommendationService.deleteRecommendation(id);
    }
}