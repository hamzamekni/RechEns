package com.example.resens.ServiceImpl;

import com.example.resens.model.Matiere;
import com.example.resens.repository.MatiereRepository;
import com.example.resens.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatiereServiceImpl implements MatiereService {
    @Autowired
    private MatiereRepository matiereRepository;

    @Override
    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }

    @Override
    public Matiere getMatiereById(Long id) {
        Optional<Matiere> matiereOptional = matiereRepository.findById(id);
        return matiereOptional.orElse(null);
    }

    @Override
    public Matiere saveMatiere(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    @Override
    public void deleteMatiereById(Long id) {
        matiereRepository.deleteById(id);
    }
}