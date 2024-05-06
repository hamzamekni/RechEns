package com.example.resens.ServiceImpl;

import com.example.resens.model.Etudiant;
import com.example.resens.model.Recommendation;
import com.example.resens.model.Teacher;
import com.example.resens.repository.EtudiantRepository;
import com.example.resens.repository.RecommendationRepository;
import com.example.resens.repository.TeacherRepository;
import com.example.resens.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Recommendation saveRecommendation(Recommendation recommendation, Long etudiantId, Long teacherId) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(etudiantId);
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        System.out.println(optionalTeacher);
        if (optionalEtudiant.isPresent()){
            recommendation.setEtudiant(optionalEtudiant.get());
        }
        if (optionalTeacher.isPresent()){
            recommendation.setTeacher(optionalTeacher.get());
        }
        return recommendationRepository.save(recommendation);
    }

    @Override
    public Recommendation updateRecommendation(Long id, Recommendation updatedRecommendation) {
        Recommendation existingRecommendation = recommendationRepository.findById(id).orElse(null);
        if (existingRecommendation != null) {
            updatedRecommendation.setRecommendationId(existingRecommendation.getRecommendationId());
            return recommendationRepository.save(updatedRecommendation);
        }
        return null;
    }

    @Override
    public void deleteRecommendation(Long id) {
        recommendationRepository.deleteById(id);
    }

    @Override
    public Recommendation getRecommendationById(Long id) {
        return recommendationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Recommendation> getAllRecommendations() {
        return recommendationRepository.findAll();
    }
}

