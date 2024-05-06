package com.example.resens.controller;

import com.example.resens.model.Etudiant;
import com.example.resens.model.Recommendation;
import com.example.resens.model.Teacher;
import com.example.resens.repository.EtudiantRepository;
import com.example.resens.repository.TeacherRepository;
import com.example.resens.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Recommendation saveRecommendation(@RequestBody Recommendation recommendation,
                                             @RequestParam Long etudiantId,
                                             @RequestParam Long teacherId) {

        return recommendationService.saveRecommendation(recommendation, etudiantId, teacherId);
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