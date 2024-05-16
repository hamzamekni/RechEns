package com.example.resens.ServiceImpl;

import com.example.resens.model.Matiere;
import com.example.resens.model.NiveauEtude;
import com.example.resens.repository.MatiereRepository;
import com.example.resens.repository.NiveauEtudeRepository;
import com.example.resens.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatiereServiceImpl implements MatiereService {
    @Autowired
    private MatiereRepository matiereRepository;
    @Autowired
    private NiveauEtudeRepository niveauEtudeRepository;

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
    public Matiere updateMatiere(Long id, Matiere updatedMatiere) {
        Matiere existingMatiere = matiereRepository.findById(id).orElse(null);
        if (existingMatiere != null) {
            updatedMatiere.setMatiere_Id(existingMatiere.getMatiere_Id());
            return matiereRepository.save(updatedMatiere);
        }
        return null;
    }

    @Override
    public Matiere saveMatiere(Matiere matiere, Long niveau_etude_id) {
        Optional<NiveauEtude> optionalNiveauEtude = niveauEtudeRepository.findById(niveau_etude_id);

        if (optionalNiveauEtude.isPresent()){
            matiere.setNiveauEtude(optionalNiveauEtude.get());
        }
        return matiereRepository.save(matiere);
    }

    @Override
    public void deleteMatiereById(Long id) {
        matiereRepository.deleteById(id);
    }
}